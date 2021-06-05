package leetcode;

public class Title6_Z {

    public static void main(String[] args) {
        //printStr("abcdefghijklmn");
    }

    private static String printStr(String str, int nums) {
        char[] strChar = str.toCharArray();
        char [][] dataArr = new char[3][10];
        for(int i = 0; i < strChar.length; i++) {
            int m = i / (2*nums - 2) * 2;
            int n = i % (2*nums - 2);
            if (n < 3) {
                dataArr[n][m] = strChar[i];
            }else {
                dataArr[2][m+1] = strChar[i];
            }
        }

        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 10; j++) {
                if(dataArr[i][j] != '\u0000') {
                    stringBuffer.append(dataArr[i][j]);
                }
            }
        }
        return stringBuffer.toString();
    }
}
