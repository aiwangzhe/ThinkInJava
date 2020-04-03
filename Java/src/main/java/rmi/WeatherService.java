package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WeatherService extends Remote {
	//提供查询天气的功能
	//必须要继承Remote和抛出RemoteException异常
	String findWeather(String city) throws RemoteException;
}
