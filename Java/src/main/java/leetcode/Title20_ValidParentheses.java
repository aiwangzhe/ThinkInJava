package leetcode;

import java.util.*;

public class Title20_ValidParentheses{
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        System.out.println(calendar.getTimeInMillis());

         Solution solution = new Title20_ValidParentheses().new Solution();
        Map<String, String> map = new HashMap<>();
        map.put("aa", "1");
        map.put("bb", "2");
        map.put("cc", "3");

        map.clear();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            map.remove(key);
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            char lastChar = '\0';
            for(char c : s.toCharArray()) {
                if (c == lastChar) {
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}