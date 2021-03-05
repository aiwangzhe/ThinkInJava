package classinfo;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

    public static void main(String[] args) {
        ClassLoader loader = Test.class.getClassLoader();
        Executors.newCachedThreadPool();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
    }

}