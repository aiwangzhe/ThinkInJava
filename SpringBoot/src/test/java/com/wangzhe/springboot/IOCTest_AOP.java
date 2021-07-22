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

public class IOCTest_AOP {
	
	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
		
		//1����Ҫ�Լ���������
//		MathCalculator mathCalculator = new MathCalculator();
//		mathCalculator.div(1, 1);
		MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
		
		mathCalculator.div(1, 0);
		
		applicationContext.close();
	}

}
