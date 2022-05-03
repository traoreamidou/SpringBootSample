package com.example.application.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {
    
    /**
     * Log ouput before executing the service.
     * Target: [UserService] is include in the same class name.
     */
    @Before("execution(* *..*.*UserService.*(..))")
    public void startLog(JoinPoint jp) {
        log.info("Method start: " + jp.getSignature());
    }

     /**
     * Log ouput after executing the service.
     * Target: [UserService] is include in the same class name.
     */
    @After("execution(* *..*.*UserService.*(..))")
    public void endLog(JoinPoint jp) {
        log.info("Method end: " + jp.getSignature());
    }

    /** Log ouput before and after the controller is executed */
    //@Around("bean(*Controller)")
    //@Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    @Around("@within(org.springframework.stereotype.Controller)")
    public Object startLog(ProceedingJoinPoint jp) throws Throwable {
        log.info("Method start: " + jp.getSignature());
        try {
            Object result = jp.proceed();
            log.info("Method end: " + jp.getSignature());
            return result;
        } catch (Exception e) {
            log.error("Method abend: ", jp.getSignature());
            throw e;
        }
    }
}
