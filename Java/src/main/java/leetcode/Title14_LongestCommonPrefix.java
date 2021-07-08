package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Title14_LongestCommonPrefix {

    public static void main(String[] args) {
        //System.out.print(longestCommonPrefix("CML"));
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 1 || strs[0].length() == 0) {
            return strs[0];
        }
        boolean stop = false;
        int k = 0;
        StringBuilder sb = new StringBuilder();
        while (!stop) {
            for(int i = 0 ; i < strs.length - 1 ; i++) {
                String str1 = strs[i];
                String str2 = strs[i+1];
                if(k < str1.length() && k < str2.length()) {
                    if(strs[i].charAt(k) != strs[i+1].charAt(k)) {
                        stop = true;
                    }
                }else {
                    stop = true;
                }
            }
            if(!stop) {
                sb.append(strs[0].charAt(k));
                k++;
            }
        }
        return sb.toString();
    }
}
