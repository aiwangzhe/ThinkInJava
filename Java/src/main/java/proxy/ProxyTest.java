package proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    /**
     * @author Gjing
     * 定义一个产家
     **/
    public interface Producer2 {
        void sell();
    }

    /**
     * @author Gjing
     * 定义商家
     **/
    public static class Canteen2 implements Producer2 {
        @Override
        public void sell() {
            System.out.println("小卖部进行卖货");
        }
    }

    public static class MyInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(new Canteen2(), args);
        }
    }

    public static void main(String[] args) {
        Producer2 producer2 = new Canteen2();
        Producer2 producerProxy = (Producer2) Proxy.newProxyInstance(producer2.getClass().getClassLoader(),
                producer2.getClass().getInterfaces(), new MyInvocationHandler());
        producerProxy.sell();

        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", Canteen2.class.getInterfaces());
        String path = "Canteen2Proxy.class";
        try(FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }
}
