package com.wangzhe.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.net.NetUtils;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.yarn.api.ApplicationConstants;
import org.apache.hadoop.yarn.api.protocolrecords.RegisterApplicationMasterResponse;
import org.apache.hadoop.yarn.api.records.*;
import org.apache.hadoop.yarn.client.ClientRMProxy;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.client.api.YarnClientApplication;
import org.apache.hadoop.yarn.client.api.async.AMRMClientAsync;
import org.apache.hadoop.yarn.client.api.async.NMClientAsync;
import org.apache.hadoop.yarn.client.api.async.impl.NMClientAsyncImpl;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.hadoop.yarn.security.AMRMTokenIdentifier;
import org.apache.hadoop.yarn.util.Records;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.*;

public class UnmanagedApplication {
    static private Logger LOG = Logger.getLogger(UnmanagedApplication.class);
    private YarnClient yarnClient;
    private boolean amCompleted = false;
    private static final long AM_STATE_WAIT_TIMEOUT_MS = 10000;
    AMRMClientAsync amRMClient = null;
    NMClientAsyncImpl amNMClient = null;

    public static void main(String[] args) throws IOException, YarnException {
        UnmanagedApplication unmanagedApplication = new UnmanagedApplication();
        unmanagedApplication.run();
    }

    public boolean run() throws IOException, YarnException {
        yarnClient = YarnClient.createYarnClient();
        yarnClient.init(new Configuration());
        yarnClient.start();
        try {
            YarnClientApplication application = yarnClient.createApplication();
            ApplicationSubmissionContext appContext = application.getApplicationSubmissionContext();
            ApplicationId appId = appContext.getApplicationId();

            // set the application name
            appContext.setApplicationName("unmanaged-application");

            // Set the priority for the application master
            Priority pri = Records.newRecord(Priority.class);
            pri.setPriority(0);
            appContext.setPriority(pri);

            // Set the queue to which this application is to be submitted in the RM
            appContext.setQueue("default");

            // Set up the container launch context for the application master
            ContainerLaunchContext amContainer = Records
                    .newRecord(ContainerLaunchContext.class);
            appContext.setAMContainerSpec(amContainer);

            // unmanaged AM
            appContext.setUnmanagedAM(true);
            LOG.info("Setting unmanaged AM");

            // Submit the application to the applications manager
            LOG.info("Submitting application to ASM");
            yarnClient.submitApplication(appContext);

            ApplicationReport appReport =
                    monitorApplication(appId, EnumSet.of(YarnApplicationState.ACCEPTED,
                            YarnApplicationState.KILLED, YarnApplicationState.FAILED,
                            YarnApplicationState.FINISHED));

            if (appReport.getYarnApplicationState() == YarnApplicationState.ACCEPTED) {
                // Monitor the application attempt to wait for launch state
                ApplicationAttemptReport attemptReport =
                        monitorCurrentAppAttempt(appId,
                                YarnApplicationAttemptState.LAUNCHED);
                ApplicationAttemptId attemptId =
                        attemptReport.getApplicationAttemptId();
                LOG.info("Launching AM with application attempt id " + attemptId);
                // launch AM
                launchMaster(attemptId);
                // Monitor the application for end state
                appReport =
                        monitorApplication(appId, EnumSet.of(YarnApplicationState.KILLED,
                                YarnApplicationState.FAILED, YarnApplicationState.FINISHED));
            }

            YarnApplicationState appState = appReport.getYarnApplicationState();
            FinalApplicationStatus appStatus = appReport.getFinalApplicationStatus();

            LOG.info("App ended with state: " + appReport.getYarnApplicationState()
                    + " and status: " + appStatus);

            boolean success;
            if (YarnApplicationState.FINISHED == appState
                    && FinalApplicationStatus.SUCCEEDED == appStatus) {
                LOG.info("Application has completed successfully.");
                success = true;
            } else {
                LOG.info("Application did finished unsuccessfully." + " YarnState="
                        + appState.toString() + ", FinalStatus=" + appStatus.toString());
                success = false;
            }

            return success;
        } finally {
            yarnClient.stop();
        }
    }

    public void launchMaster(ApplicationAttemptId attemptId) throws IOException, YarnException {
        Credentials credentials = new Credentials();
        Token<AMRMTokenIdentifier> token = yarnClient.getAMRMToken(attemptId.getApplicationId());
        credentials.addToken(token.getService(), token);

        File tokenFile = File.createTempFile("unmanagedAMRMToken","",
                new File(System.getProperty("user.dir")));
        try {
            FileUtil.chmod(tokenFile.getAbsolutePath(), "600");
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        tokenFile.deleteOnExit();
        try (DataOutputStream os = new DataOutputStream(
                new FileOutputStream(tokenFile, true))) {
            credentials.writeTokenStorageToStream(os);
        }

        Configuration conf = new Configuration();
        UserGroupInformation currentUGI = UserGroupInformation.getCurrentUser();
        currentUGI.addToken(token);
        token.setService(ClientRMProxy.getAMRMTokenService(conf));

        amRMClient = AMRMClientAsync.createAMRMClientAsync(
                1000, new UnmanagedApplication.RMCallbackHandler());
        amRMClient.init(conf);
        amRMClient.start();
        amRMClient.registerApplicationMaster(NetUtils.getHostname(), -1, "");

        // 2. Create nmClientAsync
        amNMClient = new NMClientAsyncImpl(new UnmanagedApplication.NMCallbackHandler());
        amNMClient.init(conf);
        amNMClient.start();

        // 3. register with RM and this will heartbeating to RM
        RegisterApplicationMasterResponse response = amRMClient
                .registerApplicationMaster(NetUtils.getHostname(), -1, "");

//        ArrayList<String> envAMList = new ArrayList<String>();
//        ContainerId containerId = ContainerId.newContainerId(attemptId, 0);
//        String hostname = InetAddress.getLocalHost().getHostName();
//        envAMList.add(ApplicationConstants.Environment.CONTAINER_ID.name() + "=" + containerId);
////        envAMList.add(ApplicationConstants.Environment.NM_HOST.name() + "=" + hostname);
////        envAMList.add(ApplicationConstants.Environment.NM_HTTP_PORT.name() + "=0");
////        envAMList.add(ApplicationConstants.Environment.NM_PORT.name() + "=0");
//        envAMList.add(ApplicationConstants.Environment.LOCAL_DIRS.name() + "= /tmp");
//        envAMList.add(ApplicationConstants.APP_SUBMIT_TIME_ENV + "="
//                + System.currentTimeMillis());
//
//        envAMList.add(ApplicationConstants.CONTAINER_TOKEN_FILE_ENV_NAME + "=" +
//                tokenFile.getAbsolutePath());
//
//        String[] envAM = new String[envAMList.size()];
//        String amCmd = "sleep 1000";
//        Process amProc = Runtime.getRuntime().exec(amCmd, envAMList.toArray(envAM));
//
//        final BufferedReader errReader =
//                new BufferedReader(new InputStreamReader(
//                        amProc.getErrorStream(), Charset.forName("UTF-8")));
//        final BufferedReader inReader =
//                new BufferedReader(new InputStreamReader(
//                        amProc.getInputStream(), Charset.forName("UTF-8")));
//
//        // read error and input streams as this would free up the buffers
//        // free the error stream buffer
//        Thread errThread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    String line = errReader.readLine();
//                    while((line != null) && !isInterrupted()) {
//                        System.err.println(line);
//                        line = errReader.readLine();
//                    }
//                } catch(IOException ioe) {
//                    LOG.warn("Error reading the error stream", ioe);
//                }
//            }
//        };
//        Thread outThread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    String line = inReader.readLine();
//                    while((line != null) && !isInterrupted()) {
//                        System.out.println(line);
//                        line = inReader.readLine();
//                    }
//                } catch(IOException ioe) {
//                    LOG.warn("Error reading the out stream", ioe);
//                }
//            }
//        };
//        try {
//            errThread.start();
//            outThread.start();
//        } catch (IllegalStateException ise) { }
//
//        // wait for the process to finish and check the exit code
//        try {
//            int exitCode = amProc.waitFor();
//            LOG.info("AM process exited with value: " + exitCode);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            // make sure that the error thread exits
//            // on Windows these threads sometimes get stuck and hang the execution
//            // timeout and join later after destroying the process.
//            errThread.join();
//            outThread.join();
//            errReader.close();
//            inReader.close();
//        } catch (InterruptedException ie) {
//            LOG.info("ShellExecutor: Interrupted while reading the error/out stream",
//                    ie);
//        } catch (IOException ioe) {
//            LOG.warn("Error while closing the error/out stream", ioe);
//        }finally {
//            amCompleted = true;
//        }
//        amProc.destroy();
    }

    private class RMCallbackHandler implements AMRMClientAsync.CallbackHandler {



        public void onContainersCompleted(List<ContainerStatus> statuses) {
            for (ContainerStatus status : statuses) {
                LOG.info("Container Completed: " + status.getContainerId().toString()
                        + " exitStatus="+ status.getExitStatus());
                if (status.getExitStatus() != 0) {
                    // restart
                }
                ContainerId id = status.getContainerId();
                //runningContainers.remove(id);
                //numCompletedConatiners.addAndGet(1);
            }
        }

        public void onContainersAllocated(List<Container> containers) {
            for (Container c : containers) {
                LOG.info("Container Allocated"
                        + ", id=" + c.getId()
                        + ", containerNode=" + c.getNodeId());
                //exeService.submit(new ApplicationMaster.LaunchContainerTask(c));
                //runningContainers.put(c.getId(), c);
            }
        }

        public void onShutdownRequest() {
        }

        public void onNodesUpdated(List<NodeReport> updatedNodes) {

        }

        public float getProgress() {
            float progress = 0;
            return progress;
        }

        public void onError(Throwable e) {
            amRMClient.stop();
        }

    }

    private class NMCallbackHandler implements NMClientAsync.CallbackHandler {

        public void onContainerStarted(ContainerId containerId,
                                       Map<String, ByteBuffer> allServiceResponse) {
            LOG.info("Container Stared " + containerId.toString());

        }

        public void onContainerStatusReceived(ContainerId containerId,
                                              ContainerStatus containerStatus) {

        }

        public void onContainerStopped(ContainerId containerId) {
            // TODO Auto-generated method stub

        }

        public void onStartContainerError(ContainerId containerId, Throwable t) {
            // TODO Auto-generated method stub

        }

        public void onGetContainerStatusError(ContainerId containerId,
                                              Throwable t) {
            // TODO Auto-generated method stub

        }

        public void onStopContainerError(ContainerId containerId, Throwable t) {
            // TODO Auto-generated method stub

        }

    }


    private ApplicationAttemptReport monitorCurrentAppAttempt(
            ApplicationId appId, YarnApplicationAttemptState attemptState)
            throws YarnException, IOException {
        long startTime = System.currentTimeMillis();
        ApplicationAttemptId attemptId = null;
        while (true) {
            if (attemptId == null) {
                attemptId =
                        yarnClient.getApplicationReport(appId)
                                .getCurrentApplicationAttemptId();
            }
            ApplicationAttemptReport attemptReport = null;
            if (attemptId != null) {
                attemptReport = yarnClient.getApplicationAttemptReport(attemptId);
                if (attemptState.equals(attemptReport.getYarnApplicationAttemptState())) {
                    return attemptReport;
                }
            }
            LOG.info("Current attempt state of " + appId + " is " + (attemptReport == null
                    ? " N/A " : attemptReport.getYarnApplicationAttemptState())
                    + ", waiting for current attempt to reach " + attemptState);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOG.warn("Interrupted while waiting for current attempt of " + appId
                        + " to reach " + attemptState);
            }
            if (System.currentTimeMillis() - startTime > AM_STATE_WAIT_TIMEOUT_MS) {
                String errmsg =
                        "Timeout for waiting current attempt of " + appId + " to reach "
                                + attemptState;
                LOG.error(errmsg);
                throw new RuntimeException(errmsg);
            }
        }
    }

    /**
     * Monitor the submitted application for completion. Kill application if time
     * expires.
     *
     * @param appId
     *          Application Id of application to be monitored
     * @return true if application completed successfully
     * @throws YarnException
     * @throws IOException
     */
    private ApplicationReport monitorApplication(ApplicationId appId,
                                                 Set<YarnApplicationState> finalState) throws YarnException,
            IOException {

        long foundAMCompletedTime = 0;
        StringBuilder expectedFinalState = new StringBuilder();
        boolean first = true;
        for (YarnApplicationState state : finalState) {
            if (first) {
                first = false;
                expectedFinalState.append(state.name());
            } else {
                expectedFinalState.append("," + state.name());
            }
        }

        while (true) {

            // Check app status every 1 second.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOG.debug("Thread sleep in monitoring loop interrupted");
            }

            // Get application report for the appId we are interested in
            ApplicationReport report = yarnClient.getApplicationReport(appId);

            LOG.info("Got application report from ASM for" + ", appId="
                    + appId.getId() + ", appAttemptId="
                    + report.getCurrentApplicationAttemptId() + ", clientToAMToken="
                    + report.getClientToAMToken() + ", appDiagnostics="
                    + report.getDiagnostics() + ", appMasterHost=" + report.getHost()
                    + ", appQueue=" + report.getQueue() + ", appMasterRpcPort="
                    + report.getRpcPort() + ", appStartTime=" + report.getStartTime()
                    + ", yarnAppState=" + report.getYarnApplicationState().toString()
                    + ", distributedFinalState="
                    + report.getFinalApplicationStatus().toString() + ", appTrackingUrl="
                    + report.getTrackingUrl() + ", appUser=" + report.getUser());

            YarnApplicationState state = report.getYarnApplicationState();
            if (finalState.contains(state)) {
                return report;
            }

            // wait for 10 seconds after process has completed for app report to
            // come back
            if (amCompleted) {
                if (foundAMCompletedTime == 0) {
                    foundAMCompletedTime = System.currentTimeMillis();
                } else if ((System.currentTimeMillis() - foundAMCompletedTime)
                        > AM_STATE_WAIT_TIMEOUT_MS) {
                    LOG.warn("Waited " + AM_STATE_WAIT_TIMEOUT_MS/1000
                            + " seconds after process completed for AppReport"
                            + " to reach desired final state. Not waiting anymore."
                            + "CurrentState = " + state
                            + ", ExpectedStates = " + expectedFinalState.toString());
                    throw new RuntimeException("Failed to receive final expected state"
                            + " in ApplicationReport"
                            + ", CurrentState=" + state
                            + ", ExpectedStates=" + expectedFinalState.toString());
                }
            }
        }
    }
}
