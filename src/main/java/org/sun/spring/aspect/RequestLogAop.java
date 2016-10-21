package org.sun.spring.aspect;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Arrays;

import static org.springframework.web.context.request.RequestContextHolder.getRequestAttributes;

@Aspect
@Component
@Order(4)
public class RequestLogAop {

    private final Logger logger = Logger.getLogger(getClass());

    private static Gson gson = new Gson();

    @Pointcut("execution( * org.sun.spring.controller.*.*(..))")
    public void pointCutAt() {
    }

//    @Before("pointCutAt()")
//    public void beforeAction() {
//        logger.info("在处理请求前，必须经过我！！！");
//    }

    @Around("pointCutAt()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        HttpSession session = request.getSession();
        String ip = request.getRemoteAddr();
        System.out.println(ip);
        //System.out.println(request.getRemoteHost() + ":" +request.getRemotePort());

        String url = request.getRequestURL().toString();
        String contentType = request.getContentType();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        //logger.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);
        logger.info("请求开始, 各个参数, url: " + url + ", method: " + method + ", uri: " + uri + ", Content-Type:" + contentType +", params: " + queryString);

        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        //logger.info("请求结束，controller的返回值是 " + result.toString());
        logger.info("请求结束，controller的返回值是 " + gson.toJson(result));
        return result;
    }

    @AfterThrowing(pointcut = "pointCutAt()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        HttpSession session = request.getSession();
        String ip = request.getRemoteAddr();
        System.out.println(ip);
        logger.info("异常代码:" + e.getClass().getName());
        logger.info("异常信息:" + e.getMessage());
        logger.info("异常方法:" + joinPoint.getSignature().toLongString());
        logger.info("请求参数:" + Arrays.toString(joinPoint.getArgs()));

    }


}
