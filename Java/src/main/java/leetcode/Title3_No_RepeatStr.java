package leetcode;

public class Title3_No_RepeatStr {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("sdfsdsdfjx.dfg"));
    }

    private static int lengthOfLongestSubstring(String s) {
        int []charIndex = new int[128];
        for(int i = 0 ; i < 128 ; i++) {
            charIndex[i] = -1;
        }
        int len = s.length();
        int start = 0;
        int res = 0;
        for(int i = 0 ; i < len; i++) {
            char c = s.charAt(i);
            start = Math.max(start, charIndex[c] + 1);
            res = Math.max(res, i - start + 1);
            charIndex[c] = i;
        }
        return res;
    }
}
