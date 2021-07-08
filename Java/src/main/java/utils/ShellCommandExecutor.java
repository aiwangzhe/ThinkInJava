/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName:CurlCommandExecutor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年1月10日 下午3:16:09 <br/>
 * 
 * @author lenovo
 * @version 1.0.0
 * @see
 */

public class ShellCommandExecutor {
    private static final Logger log = LoggerFactory.getLogger(ShellCommandExecutor.class);
    private static AtomicInteger atomicTaskId = new AtomicInteger(1000);

    public static String executeCmd(String cmd) {
        String result = "";
        BufferedReader reader = null;
        try {
            log.info("exec command:" + cmd);
            Process process = Runtime.getRuntime().exec(cmd);
            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            try {
                log.info("exitValue:" + process.waitFor());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                log.error(e.getMessage(), e);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            log.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    // e.printStackTrace();
                    log.error(e.getMessage(), e);
                }
            }
        }
        log.info("result:" + result);
        return result;
    }

    public static class CmdResult {
        public int exitValue;
        public String stdout;
    }
    
    public static CmdResult executeCmd(String[] cmd) {
        int exitValue = TaskExitCode.EXIT_ABNORMAL.value;
        String result = "";
        BufferedReader reader = null;
        CmdResult cmdResult = new CmdResult();
        try {
            String cmdStr = Arrays.toString(cmd);
            log.info("exec command:" + cmdStr);
            Process process=new ProcessBuilder(cmd).start();
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }

            try {
                exitValue = process.waitFor();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                log.error(e.getMessage(), e);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            log.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    // e.printStackTrace();
                    log.error(e.getMessage(), e);
                }
            }
        }

        cmdResult.exitValue = exitValue;
        cmdResult.stdout = result;
        return cmdResult;
    }

    public static interface LogHandler{
        void handleLog(String log);
    }

    public static int executeWithEOFCmd(String[] cmd, LogHandler logHandler) {
        int exitValue = -1;
        BufferedReader reader = null;
        try {
            String cmdStr = Arrays.toString(cmd);
            log.info("exec command:" + cmdStr);
            Process process=new ProcessBuilder(cmd).start();
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                log.debug("********************line: " + line);
                if (line.contains("execute remote cmd result code:")) {
                    exitValue = Integer.parseInt(line.split(":")[1].trim());
                    process.destroy();
                    break;
                }else if(logHandler != null) {
                    logHandler.handleLog(line);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            log.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    // e.printStackTrace();
                    log.error(e.getMessage(), e);
                }
            }
        }

        return exitValue;
    }

    private static File createTaskFile() throws IOException {
        FileUtils.forceMkdir(new File("/tmp/cluster-manager/"));
        String fileName = "/tmp/cluster-manager/cmd-" + atomicTaskId.getAndIncrement() + ".txt";
        File file = new File(fileName);
        if(file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }
    
    public static CmdResult executeCmd(List<String> cmdList, long timeout) {
        int exitValue = TaskExitCode.EXIT_ABNORMAL.value;
        String result = "";
        BufferedReader reader = null;
        try {
            File taskFile = createTaskFile();
            ProcessBuilder pb = new ProcessBuilder(cmdList)
                    .redirectError(taskFile).redirectOutput(taskFile);
            //pb.redirectInput(taskFile);
            //pb.redirectError(taskFile);
            try {
                Process p = pb.start();
                long startTime = System.currentTimeMillis();
                do {
                    try {
                        exitValue = p.exitValue();
                    } catch (IllegalThreadStateException e) {
                        exitValue = TaskExitCode.TIME_OUT.value;
                        System.out.println("exitValue: " + exitValue);
                    }
                    // Check if process has terminated once per second
                    Thread.sleep(1000);
                } while (System.currentTimeMillis() - startTime < timeout);

                reader = new BufferedReader(new FileReader(taskFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }

        CmdResult cmdResult = new CmdResult();
        cmdResult.exitValue = exitValue;
        cmdResult.stdout = result;
        return cmdResult;
    }

    private static int waitForProcessTermination(Process process, long timeout)
            throws InterruptedException {
        long startTime = System.currentTimeMillis();
        do {
            try {
                return process.exitValue();
            } catch (IllegalThreadStateException e) {
            }
            // Check if process has terminated once per second
            Thread.sleep(1000);
        } while (System.currentTimeMillis() - startTime < timeout);
        return TaskExitCode.TIME_OUT.value;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("sh");
        list.add("/home/wangzhe/IdeaProjects/ThinkInJava/Java/src/main/java/utils/test.sh");
        CmdResult cmdResult = executeCmd(list, 1000 * 5);
        System.out.println("code: " + cmdResult.exitValue);
        System.out.println("result: " + cmdResult.stdout);
    }
}
