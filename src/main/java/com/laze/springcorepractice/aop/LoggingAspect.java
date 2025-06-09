package com.laze.springcorepractice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(public * com.laze.springcorepractice.service..*.*(..))")
    private void servicePublicMethodsPointcut() {}

    @Before("servicePublicMethodsPointcut()")
    public void logBeforeExecution(JoinPoint joinPoint) {
        String classSimpleName = joinPoint.getTarget().getClass().getSimpleName();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        logger.info("AOP @Before: [Log] {} Class, {} ClassSimpleName, {} MethodName, {} MethodArgs", className, classSimpleName, methodName, Arrays.toString(methodArgs));
    }

    @AfterReturning(pointcut = "servicePublicMethodsPointcut()", returning = "returnValue")
    public void lgoAfterSuccessfulExecution(JoinPoint joinPoint, Object returnValue) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        logger.info("AOP @AfterReturning: [Log] {} 클래스 {} 메소드 실행 성공. 반환 값: {}", className, methodName, returnValue);
    }
}
