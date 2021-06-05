package string;

import utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class UnicodeTest {
    public static void main(String[] args)
            throws UnsupportedEncodingException {
        String s1 = "一";
        System.out.println("字符编码值： " + s1.codePointAt(0));
        System.out.println("字符编码十六进制值： " + StringUtils.toHexString(s1.charAt(0)));
        System.out.println("字符utf-8编码值： " + StringUtils.toHexString(s1.getBytes("utf-8")));
        char c1 = 19968;
        System.out.println(c1);
    }
}
