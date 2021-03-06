package com.wangzhe.springboot.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * ������
 * @author lfy
 * 
 * @Aspect�� ����Spring��ǰ����һ��������
 *
 */
@Aspect
public class LogAspects {

	@Pointcut("execution(public int com.wangzhe.springboot.aop.MathCalculator.*(..))")
	public void pointCut(){};

	@Before("pointCut()")
	public void logStart(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		System.out.println(""+joinPoint.getSignature().getName()+"�@Before:{"+Arrays.asList(args)+"}");
	}
	
	@After("com.wangzhe.springboot.aop.LogAspects.pointCut()")
	public void logEnd(JoinPoint joinPoint){
		System.out.println(""+joinPoint.getSignature().getName()+"@After");
	}
	
	//JoinPointһ��Ҫ�����ڲ�����ĵ�һλ
	@AfterReturning(value="pointCut()",returning="result")
	public void logReturn(JoinPoint joinPoint,Object result){
		System.out.println(""+joinPoint.getSignature().getName()+"@AfterReturning:н{"+result+"}");
	}
	
	@AfterThrowing(value="pointCut()",throwing="exception")
	public void logException(JoinPoint joinPoint,Exception exception){
		System.out.println(""+joinPoint.getSignature().getName()+"�쳣�������쳣��Ϣ��{"+exception+"}");
	}

}
