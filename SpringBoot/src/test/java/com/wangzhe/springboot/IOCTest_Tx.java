package com.wangzhe.springboot;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.wangzhe.springboot.aop.MathCalculator;
import com.wangzhe.springboot.bean.Boss;
import com.wangzhe.springboot.bean.Car;
import com.wangzhe.springboot.bean.Color;
import com.wangzhe.springboot.bean.Red;
import com.wangzhe.springboot.config.MainConfigOfAOP;
import com.wangzhe.springboot.config.MainConifgOfAutowired;
import com.wangzhe.springboot.dao.BookDao;
import com.wangzhe.springboot.service.BookService;
import com.wangzhe.springboot.tx.TxConfig;
import com.wangzhe.springboot.tx.UserService;

public class IOCTest_Tx {
	
	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(TxConfig.class);
	
		UserService userService = applicationContext.getBean(UserService.class);
		
		userService.insertUser();
		applicationContext.close();
	}

}
