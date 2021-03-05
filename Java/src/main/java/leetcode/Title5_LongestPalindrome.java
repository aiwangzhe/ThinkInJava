package leetcode;

public class Title5_LongestPalindrome {

    public static void main(String[] args) {

    }

    private static String longestPalindrome(String s) {
        int len = s.length();
        int maxLen = 0;
        for(int i = 0 ; i < len ; i++) {
            int highest = (len - i) / 2;
            for(int left = highest; left > i; left--) {

            }
        }
    }
