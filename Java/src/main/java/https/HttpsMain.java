package https;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.GzipFilter;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import java.io.File;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class HttpsMain {
    private static final Logger LOG = LoggerFactory.getLogger(HttpsMain.class);
    final String DISABLED_ENTRIES_SPLITTER = "\\|";

    private CertificateManager caManager;
    private Configuration configs = new Configuration();
    /**
     * The thread name prefix for threads handling agent requests.
     */
    private static final String AGENT_THREAD_POOL_NAME = "ambari-agent-thread";

    public void start() {
        caManager = new CertificateManager();
        caManager.initRootCert();
        Server serverForAgent = new Server();
        ServletContextHandler agentroot = new ServletContextHandler(
                serverForAgent, "/", ServletContextHandler.NO_SESSIONS);

        if (configs.isAgentApiGzipped()) {
            configureHandlerCompression(agentroot);
        }

        Map<String, String> configsMap = configs.getConfigsMap();
        if (configs.getAgentSSLAuthentication()) {
            String keystore = configsMap.get(Configuration.SRVR_KSTR_DIR_KEY) + File.separator
                    + configsMap.get(Configuration.KSTR_NAME_KEY);

            String truststore = configsMap.get(Configuration.SRVR_KSTR_DIR_KEY) + File.separator
                    + configsMap.get(Configuration.TSTR_NAME_KEY);

            String srvrCrtPass = configsMap.get(Configuration.SRVR_CRT_PASS_KEY);

            //SSL Context Factory
            SslContextFactory contextFactoryOneWay = new SslContextFactory(true);
            contextFactoryOneWay.setKeyStorePath(keystore);
            contextFactoryOneWay.setTrustStorePath(truststore);
            contextFactoryOneWay.setKeyStorePassword(srvrCrtPass);
            contextFactoryOneWay.setKeyManagerPassword(srvrCrtPass);
            contextFactoryOneWay.setTrustStorePassword(srvrCrtPass);
            contextFactoryOneWay.setKeyStoreType(configsMap.get(Configuration.KSTR_TYPE_KEY));
            contextFactoryOneWay.setTrustStoreType(configsMap.get(Configuration.TSTR_TYPE_KEY));
            contextFactoryOneWay.setNeedClientAuth(false);
            disableInsecureProtocols(contextFactoryOneWay);

            HttpConfiguration httpConfigurationOneWay = new HttpConfiguration();
            httpConfigurationOneWay.setSecureScheme("https");
            httpConfigurationOneWay.setSecurePort(configs.getOneWayAuthPort());
            httpConfigurationOneWay.addCustomizer(new SecureRequestCustomizer());
            setHeaderSize(httpConfigurationOneWay);

            Map<String, Integer> agentSelectorAcceptorMap = getDesiredAgentAcceptorSelector(serverForAgent);
            // SSL for 1-way auth
            ServerConnector sslConnectorOneWay = new ServerConnector(serverForAgent,
                    agentSelectorAcceptorMap.get("desiredAcceptors"), agentSelectorAcceptorMap.get("desiredSelectors"),
                    new SslConnectionFactory(contextFactoryOneWay, HttpVersion.HTTP_1_1.asString()),
                    new HttpConnectionFactory(httpConfigurationOneWay));

            sslConnectorOneWay.setPort(configs.getOneWayAuthPort());

            // Agent Jetty thread pool
            configureJettyThreadPool(serverForAgent, sslConnectorOneWay.getAcceptors(),
                    AGENT_THREAD_POOL_NAME, configs.getAgentThreadPoolSize());

            serverForAgent.addConnector(sslConnectorOneWay);
        } else {
            ServerConnector agentConnector = new ServerConnector(serverForAgent);
            agentConnector.setPort(configs.getOneWayAuthPort());
            agentConnector.setIdleTimeout(configs.getConnectionMaxIdleTime());

            // Agent Jetty thread pool
            configureJettyThreadPool(serverForAgent, agentConnector.getAcceptors(), AGENT_THREAD_POOL_NAME,
                    configs.getAgentThreadPoolSize());

            serverForAgent.addConnector(agentConnector);
        }

        ServletHolder cert = new ServletHolder(ServletContainer.class);
        cert.setInitParameter("com.sun.jersey.config.property.resourceConfigClass",
                "com.sun.jersey.api.core.PackagesResourceConfig");
        cert.setInitParameter("com.sun.jersey.config.property.packages",
                "https.unsecured.api");

        cert.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        agentroot.addServlet(cert, "/*");
        cert.setInitOrder(2);

        serverForAgent.setStopAtShutdown(true);
        try {
            serverForAgent.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new HttpsMain().start();
    }

    /**
     *  Calculate desired Acceptor and Selector for Jetty agent ServerConnector
     * @param serverForAgent
     *        the Jetty server instance which will have the selector and Acceptor set on it
     * @return jettySelectorAcceptorMap
     *         Map with "desiredAcceptors" and "desiredSelectors" keys
     */
    protected Map<String, Integer> getDesiredAgentAcceptorSelector(Server serverForAgent) {
        ServerConnector serverConnector =  new ServerConnector(serverForAgent);
        Map <String, Integer> jettySelectorAcceptorMap = new HashMap<>();
        // By default Jetty-9 assigns Math.max(1, Math.min(4, (cores available to JVM)/8)) acceptors to a ServerConnector
        int defaultAcceptors =  serverConnector.getAcceptors();

        // By default Jetty-9 assigns Math.max(1, Math.min(4, (cores available to JVM)/2))) selectors to a ServerConnector
        int defaultSelectors = serverConnector.getSelectorManager().getSelectorCount();

        // because there are two connectors sharing the same pool, cut each's
        // acceptors and selectors in half
        int desiredAcceptors = Math.max(2, defaultAcceptors / 2);
        int desiredSelectors = Math.max(2, defaultSelectors / 2);
        jettySelectorAcceptorMap.put("desiredAcceptors", desiredAcceptors);
        jettySelectorAcceptorMap.put("desiredSelectors", desiredSelectors);
        return jettySelectorAcceptorMap;
    }

    protected void configureJettyThreadPool(Server server, int acceptorThreads,
                                            String threadPoolName, int configuredThreadPoolSize) {
        int minumumAvailableThreads = 20;

        // multiply by two since there is 1 selector for every acceptor
        int reservedJettyThreads = acceptorThreads * 2;

        // this is the calculation used by Jetty
        if (configuredThreadPoolSize < reservedJettyThreads + minumumAvailableThreads) {
            int newThreadPoolSize = reservedJettyThreads + minumumAvailableThreads;

            LOG.warn(
                    "The configured Jetty {} thread pool value of {} is not sufficient on a host with {} processors. Increasing the value to {}.",
                    threadPoolName, configuredThreadPoolSize, Runtime.getRuntime().availableProcessors(),
                    newThreadPoolSize);

            configuredThreadPoolSize = newThreadPoolSize;
        }

        LOG.info(
                "Jetty is configuring {} with {} reserved acceptors/selectors and a total pool size of {} for {} processors.",
                threadPoolName, acceptorThreads * 2, configuredThreadPoolSize,
                Runtime.getRuntime().availableProcessors());

        final QueuedThreadPool qtp = server.getBean(QueuedThreadPool.class);
        qtp.setName(threadPoolName);
        qtp.setMaxThreads(configuredThreadPoolSize);
    }

    protected void configureHandlerCompression(ServletContextHandler context) {
        if (configs.isApiGzipped()) {
            FilterHolder gzipFilter = context.addFilter(GzipFilter.class, "/*",
                    EnumSet.of(DispatcherType.REQUEST));

            gzipFilter.setInitParameter("methods", "GET,POST,PUT,DELETE");
            gzipFilter.setInitParameter("mimeTypes",
                    "text/html,text/plain,text/xml,text/css,application/x-javascript," +
                            "application/xml,application/x-www-form-urlencoded," +
                            "application/javascript,application/json");
            gzipFilter.setInitParameter("minGzipSize", configs.getApiGzipMinSize());
        }
    }

    /**
     * Propagate header size to Jetty HTTP configuration
     */
    private void setHeaderSize(HttpConfiguration httpConfiguration) {
        httpConfiguration.setResponseHeaderSize(configs.getHttpResponseHeaderSize());
        httpConfiguration.setRequestHeaderSize(configs.getHttpRequestHeaderSize());
    }

    /**
     * Disables insecure protocols and cipher suites (exact list is defined
     * at server properties)
     */
    private void disableInsecureProtocols(SslContextFactory factory) {
        // by default all protocols should be available
        factory.setExcludeProtocols();
        factory.setIncludeProtocols(new String[] { "SSLv2Hello","TLSv1"});

        if (!configs.getSrvrDisabledCiphers().isEmpty()) {
            String[] masks = configs.getSrvrDisabledCiphers().split(DISABLED_ENTRIES_SPLITTER);
            factory.setExcludeCipherSuites(masks);
        }
        if (!configs.getSrvrDisabledProtocols().isEmpty()) {
            String[] masks = configs.getSrvrDisabledProtocols().split(DISABLED_ENTRIES_SPLITTER);
            factory.setExcludeProtocols(masks);
        }
    }
}
