package org.sun.spring.aspect;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.lang.annotation.Annotation;
import java.util.Arrays;

@Aspect
@Component
@Order(3)
public class AuthCheckAop {

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


        logger.info("=====AuthCheck aop======");
/*

        logger.info("" + pjp.getKind());
        logger.info("signature name:" + pjp.getSignature().getName());
        logger.info("getDeclaringTypeName :" + pjp.getSignature().getDeclaringTypeName());
        logger.info("toLongString : " + pjp.getSignature().toLongString());
        logger.info("toShortString : " + pjp.getSignature().toShortString());
        logger.info("target toString : " + pjp.getTarget().toString());
*/

        //logger.info("args : " + Arrays.toString(pjp.getArgs()));


        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = null;

        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }

        methodSignature = (MethodSignature) signature;
        Object target = pjp.getTarget();
        java.lang.reflect.Method method = target.getClass().getMethod(methodSignature.getName(),methodSignature.getParameterTypes());

        for (Annotation annotation : method.getAnnotations()) {
            Class<?> ac = annotation.annotationType();
            //logger.info(method.getName()+" : "+annotation.toString());
            if (ac == RolesAllowed.class) {
                for(String role : getAllowRoles(annotation)) {
                    logger.info("allow role:" + role);
                }
            } else if (ac == PermitAll.class){
                logger.info("permit all.");
                break;
            } else if (ac == DenyAll.class) {
                throw new Exception("You do not have access rights to this resource, please contact your administrator. ");
            }
        }

        return pjp.proceed();

    }

    private static String[] getAllowRoles(Annotation roles){
        return ((RolesAllowed)roles).value();
    }

}
