package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String toHexString(char c) {
        return Integer.toHexString(c);
    }

    public static String toHexString(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        // 利用位运算进行转换，可以看作方法二的变型
        for (byte b : bytes) {
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }

        return new String(buf);
    }

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
//        Pattern pattern = Pattern.compile("[\\w\\s-\\*/\\\\:\\.;~\\[].*");
//        Matcher matcher = pattern.matcher("[Done]");
//        while (matcher.find()) {
//            System.out.println("match: " + matcher.group(0));
//        }

        Pattern pattern = Pattern.compile("There are (\\d*) stale alerts .*");
        Matcher matcher = pattern.matcher("There are 16 stale alerts from 3 hosts xcvsdf sfsdf");
        if(matcher.matches()) {
            System.out.println(matcher.group(1));
        }

        String str1 = "test";
        String str2 = str1.substring(str1.indexOf(".") + 1);
        System.out.println(str2);
    }
}
