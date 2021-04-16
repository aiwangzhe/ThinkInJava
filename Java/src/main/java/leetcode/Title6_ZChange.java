package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Title6_ZChange {

    public static void main(String[] args) {
        System.out.println(convert("abchsdfke", 4));
    }

    private static String convert(String s, int numRows) {
        List<StringBuffer> temp = new ArrayList<>(numRows);
        String res = "";
        if(s.isEmpty() || numRows < 1) return res;
        for(int i = 0; i < numRows; i++) {
            temp.add(new StringBuffer());
        }
        int len = s.length();
        for(int i = 0 ; i < len; i++) {
            int index = i / (numRows - 1);
            int curPos = i % (numRows - 1);
            if(index % 2 == 0){  //结果为偶数或0
                temp.get(curPos).append(s.charAt(i)); //按余数正序保存
            }
            if(index % 2 != 0){  //结果为奇数
                temp.get(numRows - curPos - 1).append(s.charAt(i)); //按余数倒序保存
            }
        }
        for(int i = 0; i < numRows; i++) {
            res += temp.get(i).toString();
        }
        return res;
    }

}

