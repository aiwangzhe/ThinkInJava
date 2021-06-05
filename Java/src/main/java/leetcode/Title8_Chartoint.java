package leetcode;

public class Title8_Chartoint {

    public static void main(String[] args) {
        System.out.print(myAtoi(" -42"));
    }

    private static int myAtoi(String s) {
        s= s.trim();
        int len = s.trim().length();
        long n = 0;
        boolean negative = false;
        for(int i = 0 ; i < len ; i++) {
            char c = s.charAt(i);
            if(c == '-' && i == 0) {
                negative = true;
            }else if(c == '+' && i == 0) {
                negative = false;
            }else if(48 <= c && c <= 57) {
                n = n * 10 + (c - 48);
                if(negative && (n * (-1) < Integer.MIN_VALUE)) {
                    n = Integer.MIN_VALUE;
                    break;
                }else if(!negative && n > Integer.MAX_VALUE) {
                    n = Integer.MAX_VALUE;
                    break;
                }
            }else {
                break;
            }
        }
        if(negative && n != Integer.MIN_VALUE) {
            n = n *(-1);
        }
        return (int) n;
    }
}
