package leetcode;

public class Title7_Reverse {

    public static void main(String[] args) {
        System.out.print(revertX(1234567899));
    }

    private static int revertX(int x) {
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;
            x = x/10;
        }
        return (int)n==n? (int)n:0;
    }
}
