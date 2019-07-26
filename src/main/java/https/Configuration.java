/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package https;

import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ShellCommandUtil;

import java.io.*;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPublicKey;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * Ambari configuration.
 * Reads properties from config.properties
 */
public class Configuration {
  private static final Logger LOG = LoggerFactory.getLogger(Configuration.class);

  public static final String CONFIG_FILE = "config.properties";
  public static final String API_USE_SSL = "api.ssl";
  public static final String API_CSRF_PREVENTION_KEY = "api.csrfPrevention.enabled";
  public static final String API_GZIP_COMPRESSION_ENABLED_KEY = "api.gzip.compression.enabled";
  public static final String API_GZIP_MIN_COMPRESSION_SIZE_KEY = "api.gzip.compression.min.size";
  public static final String AGENT_API_GZIP_COMPRESSION_ENABLED_KEY = "agent.api.gzip.compression.enabled";
  public static final String AGENT_USE_SSL = "agent.ssl";
  public static final String SRVR_TWO_WAY_SSL_KEY = "security.server.two_way_ssl";
  public static final String SRVR_TWO_WAY_SSL_PORT_KEY = "security.server.two_way_ssl.port";
  public static final String SRVR_ONE_WAY_SSL_PORT_KEY = "security.server.one_way_ssl.port";
  public static final String SRVR_KSTR_DIR_KEY = "security.server.keys_dir";
  public static final String SRVR_CRT_NAME_KEY = "security.server.cert_name";
  public static final String SRVR_CSR_NAME_KEY = "security.server.csr_name";
  public static final String SRVR_KEY_NAME_KEY = "security.server.key_name";
  public static final String KSTR_NAME_KEY = "security.server.keystore_name";
  public static final String KSTR_TYPE_KEY = "security.server.keystore_type";
  public static final String TSTR_NAME_KEY = "security.server.truststore_name";
  public static final String TSTR_TYPE_KEY = "security.server.truststore_type";
  public static final String SRVR_CRT_PASS_FILE_KEY = "security.server.crt_pass_file";
  public static final String SRVR_CRT_PASS_KEY = "security.server.crt_pass";
  public static final String SRVR_CRT_PASS_LEN_KEY = "security.server.crt_pass.len";
  public static final String PASSPHRASE_ENV_KEY = "security.server.passphrase_env_var";
  public static final String PASSPHRASE_KEY = "security.server.passphrase";
  public static final String SRVR_DISABLED_CIPHERS = "security.server.disabled.ciphers";
  public static final String SRVR_DISABLED_PROTOCOLS = "security.server.disabled.protocols";
  public static final String JAVA_HOME_KEY = "java.home";
  public static final String JAVA_8_HOME_KEY = "java8.home";
  public static final String JDK_NAME_KEY = "jdk.name";
  public static final String JCE_NAME_KEY = "jce.name";
  public static final float  JDK_MIN_VERSION = 1.7f;
  public static final String CLIENT_SECURITY_KEY = "client.security";
  public static final String CLIENT_API_PORT_KEY = "client.api.port";
  public static final String CLIENT_API_SSL_PORT_KEY = "client.api.ssl.port";
  public static final String CLIENT_API_SSL_KSTR_DIR_NAME_KEY = "client.api.ssl.keys_dir";
  public static final String CLIENT_API_SSL_KSTR_NAME_KEY = "client.api.ssl.keystore_name";
  public static final String CLIENT_API_SSL_KSTR_TYPE_KEY = "client.api.ssl.keystore_type";
  public static final String CLIENT_API_SSL_TSTR_NAME_KEY = "client.api.ssl.truststore_name";
  public static final String CLIENT_API_SSL_TSTR_TYPE_KEY = "client.api.ssl.truststore_type";
  public static final String CLIENT_API_SSL_CRT_NAME_KEY = "client.api.ssl.cert_name";
  public static final String CLIENT_API_SSL_CRT_PASS_FILE_NAME_KEY = "client.api.ssl.cert_pass_file";
  public static final String CLIENT_API_SSL_CRT_PASS_KEY = "client.api.ssl.crt_pass";
  public static final String CLIENT_API_SSL_KEY_NAME_KEY = "client.api.ssl.key_name";


  public static final String SSL_TRUSTSTORE_PATH_KEY = "ssl.trustStore.path";
  public static final String SSL_TRUSTSTORE_PASSWORD_KEY = "ssl.trustStore.password";
  public static final String SSL_TRUSTSTORE_TYPE_KEY = "ssl.trustStore.type";
  public static final String JAVAX_SSL_TRUSTSTORE = "javax.net.ssl.trustStore";
  public static final String JAVAX_SSL_TRUSTSTORE_PASSWORD = "javax.net.ssl.trustStorePassword";
  public static final String JAVAX_SSL_TRUSTSTORE_TYPE = "javax.net.ssl.trustStoreType";
  public static final String SRVR_TWO_WAY_SSL_PORT_DEFAULT = "8441";
  public static final String SRVR_ONE_WAY_SSL_PORT_DEFAULT = "8440";
  public static final String SRVR_CRT_NAME_DEFAULT = "ca.crt";
  public static final String SRVR_KEY_NAME_DEFAULT = "ca.key";
  public static final String SRVR_CSR_NAME_DEFAULT = "ca.csr";
  public static final String KSTR_NAME_DEFAULT = "keystore.p12";
  public static final String KSTR_TYPE_DEFAULT = "PKCS12";
  // By default self-signed certificates are used and we can use keystore as truststore in PKCS12 format
  // When CA signed certificates are used truststore should be created in JKS format (truststore.jks)
  public static final String TSTR_NAME_DEFAULT = "keystore.p12";
  public static final String TSTR_TYPE_DEFAULT = "PKCS12";
  public static final String CLIENT_API_SSL_KSTR_NAME_DEFAULT = "https.keystore.p12";
  public static final String CLIENT_API_SSL_KSTR_TYPE_DEFAULT = "PKCS12";
  // By default self-signed certificates are used and we can use keystore as truststore in PKCS12 format
  // When CA signed certificates are used truststore should be created in JKS format (truststore.jks)
  public static final String CLIENT_API_SSL_TSTR_NAME_DEFAULT = "https.keystore.p12";
  public static final String CLIENT_API_SSL_TSTR_TYPE_DEFAULT = "PKCS12";
  public static final String CLIENT_API_SSL_CRT_PASS_FILE_NAME_DEFAULT = "https.pass.txt";
  public static final String CLIENT_API_SSL_KEY_NAME_DEFAULT = "https.key";
  public static final String CLIENT_API_SSL_CRT_NAME_DEFAULT = "https.crt";

  public static final String DEF_ARCHIVE_EXTENSION;
  public static final String DEF_ARCHIVE_CONTENT_TYPE;


;
  private static final String SRVR_TWO_WAY_SSL_DEFAULT = "false";
  private static final String SRVR_KSTR_DIR_DEFAULT = ".";
  private static final String API_CSRF_PREVENTION_DEFAULT = "true";
  private static final String API_GZIP_COMPRESSION_ENABLED_DEFAULT = "true";
  private static final String API_GZIP_MIN_COMPRESSION_SIZE_DEFAULT = "10240";
  private static final String SRVR_CRT_PASS_FILE_DEFAULT = "pass.txt";
  private static final String SRVR_CRT_PASS_LEN_DEFAULT = "50";
  private static final String SRVR_DISABLED_CIPHERS_DEFAULT = "";
  private static final String SRVR_DISABLED_PROTOCOLS_DEFAULT = "";
  private static final String PASSPHRASE_ENV_DEFAULT = "AMBARI_PASSPHRASE";

  public static final String SERVER_HTTP_REQUEST_HEADER_SIZE = "server.http.request.header.size";
  public static final String SERVER_HTTP_RESPONSE_HEADER_SIZE = "server.http.response.header.size";
  public static final int SERVER_HTTP_REQUEST_HEADER_SIZE_DEFAULT = 64*1024;
  public static final int SERVER_HTTP_RESPONSE_HEADER_SIZE_DEFAULT = 64*1024;
  private static final String CLIENT_THREADPOOL_SIZE_KEY = "client.threadpool.size.max";
  private static final int CLIENT_THREADPOOL_SIZE_DEFAULT = 25;
  private static final String AGENT_THREADPOOL_SIZE_KEY = "agent.threadpool.size.max";
  private static final int AGENT_THREADPOOL_SIZE_DEFAULT = 25;
  private static final String SERVER_CONNECTION_MAX_IDLE_TIME = "server.connection.max.idle.millis";


  private static final int CLIENT_API_PORT_DEFAULT = 8080;
  private static final int CLIENT_API_SSL_PORT_DEFAULT = 8443;


  private Properties properties;
  private JsonObject hostChangesJson;
  private Map<String, String> configsMap;
  private Map<String, String> agentConfigsMap;

  static {
    if (System.getProperty("os.name").contains("Windows")) {
      DEF_ARCHIVE_EXTENSION = ".zip";
      DEF_ARCHIVE_CONTENT_TYPE = "application/zip";
    }
    else {
      DEF_ARCHIVE_EXTENSION = ".tar.gz";
      DEF_ARCHIVE_CONTENT_TYPE = "application/x-ustar";
    }
  }

  public Configuration() {
    this(readConfigFile());
  }

  /**
   * This constructor is called from default constructor and
   * also from most tests.
   * @param properties properties to use for testing and in production using
   * the Conf object.
   */
  public Configuration(Properties properties) {
    this.properties = properties;
    configsMap = new HashMap<>();
    agentConfigsMap = new HashMap<>();

    configsMap.put(SRVR_TWO_WAY_SSL_KEY, properties.getProperty(
      SRVR_TWO_WAY_SSL_KEY, SRVR_TWO_WAY_SSL_DEFAULT));
    configsMap.put(SRVR_TWO_WAY_SSL_PORT_KEY, properties.getProperty(
      SRVR_TWO_WAY_SSL_PORT_KEY, SRVR_TWO_WAY_SSL_PORT_DEFAULT));
    configsMap.put(SRVR_ONE_WAY_SSL_PORT_KEY, properties.getProperty(
      SRVR_ONE_WAY_SSL_PORT_KEY, SRVR_ONE_WAY_SSL_PORT_DEFAULT));
    configsMap.put(SRVR_KSTR_DIR_KEY, properties.getProperty(
      SRVR_KSTR_DIR_KEY, SRVR_KSTR_DIR_DEFAULT));
    configsMap.put(SRVR_CRT_NAME_KEY, properties.getProperty(
      SRVR_CRT_NAME_KEY, SRVR_CRT_NAME_DEFAULT));
    configsMap.put(SRVR_KEY_NAME_KEY, properties.getProperty(
      SRVR_KEY_NAME_KEY, SRVR_KEY_NAME_DEFAULT));
    configsMap.put(SRVR_CSR_NAME_KEY, properties.getProperty(
      SRVR_CSR_NAME_KEY, SRVR_CSR_NAME_DEFAULT));
    configsMap.put(KSTR_NAME_KEY, properties.getProperty(
      KSTR_NAME_KEY, KSTR_NAME_DEFAULT));
    configsMap.put(KSTR_TYPE_KEY, properties.getProperty(
      KSTR_TYPE_KEY, KSTR_TYPE_DEFAULT));
    configsMap.put(TSTR_NAME_KEY, properties.getProperty(
      TSTR_NAME_KEY, TSTR_NAME_DEFAULT));
    configsMap.put(TSTR_TYPE_KEY, properties.getProperty(
      TSTR_TYPE_KEY, TSTR_TYPE_DEFAULT));
    configsMap.put(SRVR_CRT_PASS_FILE_KEY, properties.getProperty(
      SRVR_CRT_PASS_FILE_KEY, SRVR_CRT_PASS_FILE_DEFAULT));
    configsMap.put(PASSPHRASE_ENV_KEY, properties.getProperty(
      PASSPHRASE_ENV_KEY, PASSPHRASE_ENV_DEFAULT));
    configsMap.put(PASSPHRASE_KEY, System.getenv(configsMap.get(
      PASSPHRASE_ENV_KEY)));
    configsMap.put(SRVR_CRT_PASS_LEN_KEY, properties.getProperty(
      SRVR_CRT_PASS_LEN_KEY, SRVR_CRT_PASS_LEN_DEFAULT));
    configsMap.put(SRVR_DISABLED_CIPHERS, properties.getProperty(
      SRVR_DISABLED_CIPHERS, SRVR_DISABLED_CIPHERS_DEFAULT));
    configsMap.put(SRVR_DISABLED_PROTOCOLS, properties.getProperty(
      SRVR_DISABLED_PROTOCOLS, SRVR_DISABLED_PROTOCOLS_DEFAULT));

    configsMap.put(CLIENT_API_SSL_KSTR_DIR_NAME_KEY, properties.getProperty(
      CLIENT_API_SSL_KSTR_DIR_NAME_KEY, configsMap.get(SRVR_KSTR_DIR_KEY)));
    configsMap.put(CLIENT_API_SSL_KSTR_NAME_KEY, properties.getProperty(
      CLIENT_API_SSL_KSTR_NAME_KEY, CLIENT_API_SSL_KSTR_NAME_DEFAULT));
    configsMap.put(CLIENT_API_SSL_KSTR_TYPE_KEY, properties.getProperty(
      CLIENT_API_SSL_KSTR_TYPE_KEY, CLIENT_API_SSL_KSTR_TYPE_DEFAULT));
    configsMap.put(CLIENT_API_SSL_TSTR_NAME_KEY, properties.getProperty(
      CLIENT_API_SSL_TSTR_NAME_KEY, CLIENT_API_SSL_TSTR_NAME_DEFAULT));
    configsMap.put(CLIENT_API_SSL_TSTR_TYPE_KEY, properties.getProperty(
      CLIENT_API_SSL_TSTR_TYPE_KEY, CLIENT_API_SSL_TSTR_TYPE_DEFAULT));
    configsMap.put(CLIENT_API_SSL_CRT_PASS_FILE_NAME_KEY, properties.getProperty(
      CLIENT_API_SSL_CRT_PASS_FILE_NAME_KEY, CLIENT_API_SSL_CRT_PASS_FILE_NAME_DEFAULT));
    configsMap.put(CLIENT_API_SSL_KEY_NAME_KEY, properties.getProperty(
      CLIENT_API_SSL_KEY_NAME_KEY, CLIENT_API_SSL_KEY_NAME_DEFAULT));
    configsMap.put(CLIENT_API_SSL_CRT_NAME_KEY, properties.getProperty(
      CLIENT_API_SSL_CRT_NAME_KEY, CLIENT_API_SSL_CRT_NAME_DEFAULT));
    configsMap.put(JAVA_HOME_KEY, properties.getProperty(
      JAVA_HOME_KEY));

    File passFile = new File(configsMap.get(SRVR_KSTR_DIR_KEY) + File.separator
      + configsMap.get(SRVR_CRT_PASS_FILE_KEY));
    String password = null;

    if (!passFile.exists()) {
      LOG.info("Generation of file with password");
      try {
        password = RandomStringUtils.randomAlphanumeric(Integer
          .parseInt(configsMap.get(SRVR_CRT_PASS_LEN_KEY)));
        FileUtils.writeStringToFile(passFile, password);
        ShellCommandUtil.setUnixFilePermissions(
          ShellCommandUtil.MASK_OWNER_ONLY_RW, passFile.getAbsolutePath());
      } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException(
          "Error reading certificate password from file");
      }
    } else {
      LOG.info("Reading password from existing file");
      try {
        password = FileUtils.readFileToString(passFile);
        password = password.replaceAll("\\p{Cntrl}", "");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    configsMap.put(SRVR_CRT_PASS_KEY, password);

    if (getApiSSLAuthentication()) {
      LOG.info("API SSL Authentication is turned on.");
      File httpsPassFile = new File(configsMap.get(CLIENT_API_SSL_KSTR_DIR_NAME_KEY)
        + File.separator + configsMap.get(CLIENT_API_SSL_CRT_PASS_FILE_NAME_KEY));

      if (httpsPassFile.exists()) {
        LOG.info("Reading password from existing file");
        try {
          password = FileUtils.readFileToString(httpsPassFile);
          password = password.replaceAll("\\p{Cntrl}", "");
        } catch (IOException e) {
          e.printStackTrace();
          throw new RuntimeException("Error reading certificate password from" +
            " file " + httpsPassFile.getAbsolutePath());
        }
      } else {
        LOG.error("There is no keystore for https UI connection.");
        LOG.error("Run \"ambari-server setup-https\" or set " + Configuration.API_USE_SSL + " = false.");
        throw new RuntimeException("Error reading certificate password from " +
          "file " + httpsPassFile.getAbsolutePath());

      }

      configsMap.put(CLIENT_API_SSL_CRT_PASS_KEY, password);
    }

  }

  /**
   * Get the map with server config parameters.
   * Keys - public constants of this class
   * @return the map with server config parameters
   */
  public Map<String, String> getConfigsMap() {
    return configsMap;
  }

  /**
   * Get the map with server config parameters related to agent configuration.
   * Keys - public constants of this class
   * @return the map with server config parameters related to agent configuration
   */
  public Map<String, String> getAgentConfigsMap() {
    return agentConfigsMap;
  }

  /**
   * Check to see if the API should be authenticated via ssl or not
   * @return false if not, true if ssl needs to be used.
   */
  public boolean getApiSSLAuthentication() {
    return ("true".equals(properties.getProperty(API_USE_SSL, "false")));
  }

  /**
   * Check to see if the Agent should be authenticated via ssl or not
   * @return false if not, true if ssl needs to be used.
   */
  public boolean getAgentSSLAuthentication() {
    return ("true".equals(properties.getProperty(AGENT_USE_SSL, "true")));
  }

  public String getSrvrDisabledCiphers() {
    String disabledCiphers = properties.getProperty(SRVR_DISABLED_CIPHERS,
            properties.getProperty(SRVR_DISABLED_CIPHERS,
                    SRVR_DISABLED_CIPHERS_DEFAULT));
    return disabledCiphers.trim();
  }

  public String getSrvrDisabledProtocols() {
    String disabledProtocols = properties.getProperty(SRVR_DISABLED_PROTOCOLS,
            properties.getProperty(SRVR_DISABLED_PROTOCOLS,
                    SRVR_DISABLED_PROTOCOLS_DEFAULT));
    return disabledProtocols.trim();
  }

  public int getOneWayAuthPort() {
    return Integer.parseInt(properties.getProperty(SRVR_ONE_WAY_SSL_PORT_KEY,
            String.valueOf(SRVR_ONE_WAY_SSL_PORT_DEFAULT)));
  }

  /**
   * @return max thread pool size for agents, default 25
   */
  public int getAgentThreadPoolSize() {
    return Integer.parseInt(properties.getProperty(
            AGENT_THREADPOOL_SIZE_KEY, String.valueOf(AGENT_THREADPOOL_SIZE_DEFAULT)));
  }

  /**
   * @return Custom property for request header size
   */
  public int getHttpRequestHeaderSize() {
    return Integer.parseInt(properties.getProperty(
            SERVER_HTTP_REQUEST_HEADER_SIZE, String.valueOf(SERVER_HTTP_REQUEST_HEADER_SIZE_DEFAULT)));
  }

  /**
   * @return Custom property for response header size
   */
  public int getHttpResponseHeaderSize() {
    return Integer.parseInt(properties.getProperty(
            SERVER_HTTP_RESPONSE_HEADER_SIZE, String.valueOf(SERVER_HTTP_RESPONSE_HEADER_SIZE_DEFAULT)));
  }

  public int getConnectionMaxIdleTime() {
    return Integer.parseInt(properties.getProperty
            (SERVER_CONNECTION_MAX_IDLE_TIME, String.valueOf("900000")));
  }

  public boolean isAgentApiGzipped() {
    return "true".equalsIgnoreCase(properties.getProperty(
            AGENT_API_GZIP_COMPRESSION_ENABLED_KEY,
            API_GZIP_COMPRESSION_ENABLED_DEFAULT));
  }

  public boolean isApiGzipped() {
    return "true".equalsIgnoreCase(properties.getProperty(
            API_GZIP_COMPRESSION_ENABLED_KEY,
            API_GZIP_COMPRESSION_ENABLED_DEFAULT));
  }

  public String getApiGzipMinSize() {
    return properties.getProperty(API_GZIP_MIN_COMPRESSION_SIZE_KEY,
            API_GZIP_MIN_COMPRESSION_SIZE_DEFAULT);
  }

  /**
   * Find, read, and parse the configuration file.
   * @return the properties that were found or empty if no file was found
   */
  private static Properties readConfigFile() {
    Properties properties = new Properties();

    //Get property file stream from classpath
    InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream(CONFIG_FILE);

    if (inputStream == null) {
      throw new RuntimeException(CONFIG_FILE + " not found in classpath");
    }

    // load the properties
    try {
      properties.load(inputStream);
      inputStream.close();
    } catch (FileNotFoundException fnf) {
      LOG.info("No configuration file " + CONFIG_FILE + " found in classpath.", fnf);
    } catch (IOException ie) {
      throw new IllegalArgumentException("Can't read configuration file " +
              CONFIG_FILE, ie);
    }

    return properties;
  }

}
