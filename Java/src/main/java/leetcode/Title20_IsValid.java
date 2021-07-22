package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Title20_IsValid {

    public static void main(String[] args) {
        int [] nums = new int[]{0, 1, 0, 0};
        System.out.print(isValid("()[]{}[["));
    }

    public static boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        int len = s.length();
        if(len % 2 != 0) {
            return false;
        }else {
            for(int i = 0 ; i < len - 1 ; i++) {
                if(map.get(s.charAt(i)) == null) {
                    return false;
                }else if(map.get(s.charAt(i)) == s.charAt(i + 1)) {
                    i++;
                }else {
                    if(map.get(s.charAt(i)) != s.charAt(len - 1 -i)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
