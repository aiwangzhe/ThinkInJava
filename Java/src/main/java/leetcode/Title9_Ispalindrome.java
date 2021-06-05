package leetcode;

public class Title9_Ispalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome(-12));
    }

    public static boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        String s = String.valueOf(x);
        int len = s.length();
        int middle = len / 2;
        int right = len - 1;
        int left = 0;
        while (left < middle) {
            if(s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            }else {
                break;
            }
        }
        if(left >= right) {
            return true;
        }
        return false;
    }
}
