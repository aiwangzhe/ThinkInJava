package jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class JMXTest {

    public static void main(String[] args)
            throws MalformedObjectNameException, NotCompliantMBeanException,
            InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        MBeanServer jmxMBeanServer = ManagementFactory.getPlatformMBeanServer();
        User userBean = new User();
        ObjectName objectName = new ObjectName("jmx.UserBean:name=" + User.class.getName());
        ObjectInstance objectInstance = jmxMBeanServer.registerMBean(userBean, objectName);
        while (true) {
            Thread.sleep(10 * 1000);
            System.out.println("sleep 10s");
        }
    }
}
