package com.wangzhe.hadoop;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FilenameUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.ClassUtil;
import org.apache.hadoop.yarn.api.ApplicationConstants;
import org.apache.hadoop.yarn.api.ApplicationConstants.Environment;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationsRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationResponse;
import org.apache.hadoop.yarn.api.records.*;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.client.api.YarnClientApplication;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.hadoop.yarn.util.ConverterUtils;
import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class Client {
	static private Logger logger = Logger.getLogger("trumanz");

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();

		System.out.println("fs.defaultFS: " + conf.get("fs.defaultFS"));
		if (UserGroupInformation.isSecurityEnabled()) {
			throw new Exception("SecurityEnabled , not support");
		}

		// 1. create and start a yarnClient
		YarnClient yarnClient = YarnClient.createYarnClient();
		yarnClient.init(conf);
		yarnClient.start();

//		GetApplicationsRequest request = GetApplicationsRequest.newInstance();
//		Set<String> applicationSet = new HashSet<>();
//		applicationSet.add(YarnApplicationState.NEW.toString());
//		request.setApplicationStates(applicationSet);
//
//		List<ApplicationReport> applicationReports = yarnClient.getApplications(request);
//		applicationReports.forEach(report -> {
//			System.out.println("report: " + report);
//		});
//		YarnClusterMetrics clusterMetrics = yarnClient.getYarnClusterMetrics();
//		System.out.println("Got Cluster metric info from ASM"
//				+ ", numNodeManagers=" + clusterMetrics.getNumNodeManagers());

//		YarnClientApplication app = yarnClient.createApplication();
//		ApplicationId appId = app.getNewApplicationResponse().getApplicationId();
//		ApplicationSubmissionContext context = app.getApplicationSubmissionContext();
//		context.setAMContainerSpec();
//		yarnClient.submitApplication()

		// 2. create an application
		long currentTime = System.currentTimeMillis();
		System.out.println("start request yarn, current time: " + currentTime);
		YarnClientApplication app = yarnClient.createApplication();
		long endTime = System.currentTimeMillis();
		System.out.println("complete request yarn, current time: " + (endTime-currentTime));
		app.getApplicationSubmissionContext()
				.setKeepContainersAcrossApplicationAttempts(false);
		app.getApplicationSubmissionContext().setApplicationName(
				"truman.ApplicationMaster");

		// 3. Set the app's resource usage, 100*10MB, 1vCPU
		Resource capability = Resource.newInstance(100, 1);
		app.getApplicationSubmissionContext().setResource(capability);


		// 4. Set the app's localResource env and command by
		// ContainerLaunchContext
		ContainerLaunchContext amContainer = createAMContainerLanunchContext(
				conf, app.getApplicationSubmissionContext().getApplicationId());
		app.getApplicationSubmissionContext().setAMContainerSpec(amContainer);




		// 5. submit to queue default
		app.getApplicationSubmissionContext().setPriority(
				Priority.newInstance(0));
		app.getApplicationSubmissionContext().setQueue("default");
		ApplicationId appId = yarnClient.submitApplication(app
				.getApplicationSubmissionContext());



		monitorApplicationReport(yarnClient, appId);

	}

	private static ContainerLaunchContext createAMContainerLanunchContext(
			Configuration conf, ApplicationId appId) throws IOException {
		//Add this jar file to hdfs
		Map<String, LocalResource> localResources = new HashMap<String, LocalResource>();
		FileSystem fs = FileSystem.get(conf);
		String thisJar = "Hadoop/target/Hadoop-1.0-jar-with-dependencies.jar";
		String thisJarBaseName = FilenameUtils.getName(thisJar);
		System.out.println("thisJar is " + thisJar);

		addToLocalResources(fs, thisJar, thisJarBaseName, appId.toString(),
				localResources);

		//Set CLASSPATH environment 
		Map<String, String> env = new HashMap<String, String>();
		StringBuilder classPathEnv = new StringBuilder(
				Environment.CLASSPATH.$$());
		classPathEnv.append(ApplicationConstants.CLASS_PATH_SEPARATOR);
		classPathEnv.append("./*");
		for (String c : conf
				.getStrings(
						YarnConfiguration.YARN_APPLICATION_CLASSPATH,
						YarnConfiguration.DEFAULT_YARN_CROSS_PLATFORM_APPLICATION_CLASSPATH)) {
			classPathEnv.append(ApplicationConstants.CLASS_PATH_SEPARATOR);
			classPathEnv.append(c.trim());
		}

		if (conf.getBoolean(YarnConfiguration.IS_MINI_YARN_CLUSTER, false)) {
			classPathEnv.append(':');
			classPathEnv.append(System.getProperty("java.class.path"));
		}
		env.put(Environment.CLASSPATH.name(), classPathEnv.toString());

		//Build the execute command
		List<String> commands = new LinkedList<String>();
		StringBuilder command = new StringBuilder();
		command.append(Environment.JAVA_HOME.$$()).append("/bin/java  ");
		command.append("-Dlog4j.configuration=container-log4j.properties ");
		command.append("-Dyarn.app.container.log.dir=" + 
				ApplicationConstants.LOG_DIR_EXPANSION_VAR + " ");
		command.append("-Dyarn.app.container.log.filesize=0 ");
		command.append("-Dhadoop.root.logger=INFO,CLA ");
		command.append("com.wangzhe.hadoop.ApplicationMaster ");
		command.append("1>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/stdout ");
		command.append("2>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/stderr ");
		commands.add(command.toString());
	
		ContainerLaunchContext amContainer = ContainerLaunchContext
				.newInstance(localResources, env, commands, null, null, null);
		return amContainer;
	}

	private static void addToLocalResources(FileSystem fs, String fileSrcPath,
			String fileDstPath, String appId,
			Map<String, LocalResource> localResources)
			throws IllegalArgumentException, IOException {

		String suffix = "mytest" + "/" + appId + "/" + fileDstPath;
		Path dst = new Path("hdfs:/", suffix);
		logger.info("hdfs copyFromLocalFile " + fileSrcPath + " =>" + dst);
		fs.copyFromLocalFile(new Path(fileSrcPath), dst);
		FileStatus scFileStatus = fs.getFileStatus(dst);
		LocalResource scRsrc = LocalResource.newInstance(
				ConverterUtils.getYarnUrlFromPath(dst), LocalResourceType.FILE,
				LocalResourceVisibility.APPLICATION, scFileStatus.getLen(),
				scFileStatus.getModificationTime());
		System.out.println("upload hdfs file completed!");
		localResources.put(fileDstPath, scRsrc);

	}

	private static void monitorApplicationReport(YarnClient yarnClient, ApplicationId appId) throws YarnException, IOException {
		while (true) {
			try {
				Thread.sleep(3 * 1000);
			} catch (InterruptedException e) {

			}
			ApplicationReport report = yarnClient.getApplicationReport(appId);
			System.out.println("Got application report " + ", clientToAMToken="
					+ report.getClientToAMToken() + ", appDiagnostics="
					+ report.getDiagnostics() + ", appMasterHost="
					+ report.getHost() + ", appQueue=" + report.getQueue()
					+ ", appMasterRpcPort=" + report.getRpcPort()
					+ ", appStartTime=" + report.getStartTime()
					+ ", yarnAppState="
					+ report.getYarnApplicationState().toString()
					+ ", distributedFinalState="
					+ report.getFinalApplicationStatus().toString()
					+ ", appTrackingUrl=" + report.getTrackingUrl()
					+ ", appUser=" + report.getUser());
			if (report.getYarnApplicationState().equals(YarnApplicationState.FINISHED) ||
					report.getYarnApplicationState().equals(YarnApplicationState.FAILED)) {
				break;
			}
		}
	}
}
