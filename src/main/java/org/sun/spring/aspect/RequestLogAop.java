package org.sun.spring.aspect;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class RequestLogAop {

    private final Logger logger = Logger.getLogger(getClass());

    private static Gson gson = new Gson();

    @Pointcut("execution( * org.sun.spring.controller.*.*(..))")
    public void pointCutAt() {
    }

    @Before("pointCutAt()")
    public void beforeAction() {
        logger.info("在处理请求前，必须经过我！！！");
    }

    @Around("pointCutAt()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        //logger.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);
        logger.info("请求开始, 各个参数, url: " + url + ", method: " + method + ", uri: " + uri + ", params: " + queryString);

        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        //logger.info("请求结束，controller的返回值是 " + result.toString());
        logger.info("请求结束，controller的返回值是 " + gson.toJson(result));
        return result;
    }

}
