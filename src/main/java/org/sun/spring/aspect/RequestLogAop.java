package org.sun.spring.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestLogAop {

	private final Logger logger = Logger.getLogger(getClass());
	
	@Pointcut("execution( * org.sun.spring.controller.*.*(..))")
	public void pointCutAt() {
		
	}
	
	@Before("pointCutAt()")
	public void beforeAction() {
		logger.fatal("在处理请求前，必须经过我！！！");
	}
	
}
