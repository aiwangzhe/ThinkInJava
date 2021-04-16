package string;

import java.util.ArrayList;
import java.util.List;

public class StringTest {
    public static void main(String[] args) {
        String str = "\033[0;37;41m 安装程序已经在运行[PID：%s]，请等待完成！\033[0m";
        str = str.replaceAll("\\033.*? ", "");
        System.out.println("str: " + str);
    }
}
