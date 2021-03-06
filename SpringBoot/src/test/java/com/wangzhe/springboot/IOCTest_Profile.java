package com.wangzhe.springboot;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.wangzhe.springboot.bean.Boss;
import com.wangzhe.springboot.bean.Car;
import com.wangzhe.springboot.bean.Color;
import com.wangzhe.springboot.bean.Red;
import com.wangzhe.springboot.bean.Yellow;
import com.wangzhe.springboot.config.MainConfigOfProfile;
import com.wangzhe.springboot.config.MainConifgOfAutowired;
import com.wangzhe.springboot.dao.BookDao;
import com.wangzhe.springboot.service.BookService;

public class IOCTest_Profile {
	
	//1��ʹ�������ж�̬����: �����������λ�ü��� -Dspring.profiles.active=test
	//2������ķ�ʽ����ĳ�ֻ�����
	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext();
		//1������һ��applicationContext
		//2��������Ҫ����Ļ���
		applicationContext.getEnvironment().setActiveProfiles("dev");
		//3��ע����������
		applicationContext.register(MainConfigOfProfile.class);
		//4������ˢ������
		applicationContext.refresh();
		
		
		String[] namesForType = applicationContext.getBeanNamesForType(DataSource.class);
		for (String string : namesForType) {
			System.out.println(string);
		}
		
		Yellow bean = applicationContext.getBean(Yellow.class);
		System.out.println(bean);
		applicationContext.close();
	}

}
