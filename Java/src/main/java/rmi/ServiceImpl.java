package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl extends UnicastRemoteObject implements WeatherService{
	//必须要有构造方法
	protected ServiceImpl() throws RemoteException {
		super();
	}
	//提供查询天气的功能：只提供西安和上海
	@Override
	public String findWeather(String city) throws RemoteException {
		if(city.equals("西安")) {
			return city+":多云，有大风，多穿衣服保暖";
		}
		else if(city.equals("上海")) {
			return city+":晴，温度40度，少穿衣服";
		}
		return "输入有误，请重新输入";
	}
}
