package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient {
    public static void main(String[] args) {
        try {
    		//1、获取注册表
            WeatherService weatherService = null;
			try {
				weatherService = 
				(WeatherService) Naming.lookup("rmi://127.0.0.1:8000/weather");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
            //2、调用服务
            String xian=weatherService.findWeather("西安");
            System.out.println(xian);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
