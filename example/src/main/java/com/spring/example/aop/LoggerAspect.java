package com.spring.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggerAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
	static String name = "";
	static String type = "";

	@Pointcut("execution(* com..controller.*Controller.*(..))")
	private void controllerPointcut() { }
	
	@Pointcut("execution(* com..impl.*Impl.*(..))")
	private void serviceImplePointcut() { }
	
	@Pointcut("execution(* com..controller.rest.*Controller.*(..))")
	private void restControllerPointcut() { }
	
	@Around("controllerPointcut() || serviceImplePointcut() || restControllerPointcut()")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		type = joinPoint.getSignature().getDeclaringTypeName();
		
		if(type.indexOf("rest") > -1 ) {
			name = "RestController  \t:  ";
		} else if (type.indexOf("Controller") > -1) {
			name = "Controller  \t:  ";
		} else if (type.indexOf("Service") > -1) {
			name = "ServiceImpl  \t:  ";
		} else if (type.indexOf("DAO") > -1) {
			name = "DAO  \t\t:  ";
		}
		logger.info(name + type + "." + joinPoint.getSignature().getName() + "()");
		return joinPoint.proceed();
	}

}
