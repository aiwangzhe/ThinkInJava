package rmi;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
        	//1、创建一个服务
        	WeatherService weatherService = new ServiceImpl();
        	//2、生成注册表
            Registry registry = LocateRegistry.createRegistry(8000);
            //4、把代理绑定到注册表上发布:weather是暗号
            Naming.rebind("rmi://127.0.0.1:8000/weather",weatherService);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
