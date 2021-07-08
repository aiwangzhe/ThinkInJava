package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Title13_RomanToInt {

    public static void main(String[] args) {
        System.out.print(romanToInt("CML"));
    }

    private static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("CD", 400);
        map.put("C", 100);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("XL", 40);
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("I", 1);

        int value = 0;
        char[] chars = s.toCharArray();
        for(int i = 0 ; i < chars.length ; i++) {
            char c = chars[i];
            if(c == 'C' || c == 'X' || c == 'I') {
                if(i < chars.length - 1) {
                    String str = c + "" + chars[i + 1];
                    if(map.containsKey(str)) {
                        value += map.get(str);
                        i++;
                        continue;
                    }
                }

            }
            value += map.get(c + "");
        }
        return value;
    }
}
