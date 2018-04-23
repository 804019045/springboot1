package com.xuyue.springboot1.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 切面截取
 */
@Aspect
@Component
public class HttpRequestAspect {
    //获取日志
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestAspect.class);

    @Pointcut("execution(public * com.xuyue.springboot1.controller.*.*(..))")
    public void log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("---------doBefore----------");
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url={}",request.getRequestURI());

        //method
        logger.info("method={}",request.getMethod());

        //ip
        logger.info("ip={}",request.getRemoteAddr());

        //类方法
        logger.info("类方法={}",joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        //参数
        logger.info("参数={}",joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter(){
        logger.info("---------doAfter----------");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object){
        if(object != null){
            logger.info("---------doAfterReturning----------");
            logger.info("response={}",object.toString());
        }
    }
}
