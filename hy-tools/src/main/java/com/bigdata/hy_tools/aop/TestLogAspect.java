package com.bigdata.hy_tools.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * @author huyi
 * @date 2020/5/26 09:59
 */
@Aspect
@Component
public class TestLogAspect {
    private final Logger logger = LoggerFactory.getLogger(TestLogAspect.class);

    @Pointcut("execution(public * com.bigdata.hy_tools.service..*.*(..))")
    public void testLog() {

    }

    @Pointcut("@annotation(com.bigdata.hy_tools.annotation.TestAnnotation)")
    public void testAnno() {
    }

    @Before("testAnno()")
    public void doBeforeTestAnno(JoinPoint joinPoint) {
        logger.info("hy的测试annotation，开始运行！");
    }

    @Before("testLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "testLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }
}
