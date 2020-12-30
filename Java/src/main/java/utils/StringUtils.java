package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static void main(String[] args) {
        String str = "sdfsfsfdatahub:\n" +
                "  ribbon:\n" +
                "    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RoundRobinRule\n" +
                "    NFLoadBalancerPingClassName: com.lenovo.leap.gateway.component.HealthExamService\n" +
                "\n" +
                "datastream:\n" +
                "  ribbon:\n" +
                "    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RoundRobinRule\n" +
                "    NFLoadBalancerPingClassName: com.lenovo.leap.gateway.component.HealthExamService";
        Pattern pattern = Pattern.compile("datahub.*HealthExamService", Pattern.DOTALL);

        Matcher matcher = pattern.matcher(str);
        String newStr = matcher.replaceAll("");
        System.out.println("newStr: " + newStr);
        if (matcher.find()) {
            System.out.println("newStr: " + newStr);
        }
    }
}
