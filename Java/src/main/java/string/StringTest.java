package string;

import utils.ShellCommandExecutor;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StringTest {
    public static void main(String[] args) {
        long time = 1626451199;
        Date date = new Date(time * 1000);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));

        //ShellCommandExecutor.executeCmd(delCmd);
        String addCmd = String.format( "sh -c echo '%s %s' >> /home/wangzhe/IdeaProjects/ThinkInJava/Java/test.txt",
                "172.171.12", "host1");
        System.out.println(addCmd);
        ShellCommandExecutor.executeCmd(addCmd);
    }
}
