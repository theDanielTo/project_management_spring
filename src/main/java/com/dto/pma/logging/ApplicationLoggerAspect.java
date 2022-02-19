package com.dto.pma.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.dto.pma.controllers..*)" +
            "|| within(com.dto.pma.dao..*)") // where logger is applied
    public void definePackagePointcuts() {
        // empty method just to name the location specified in the pointcut
    }

    @Around("definePackagePointcuts()") // After=log shows after execution, Before=before execution, Around=
    public Object logAround(ProceedingJoinPoint jp) { // joinpoint = point during the execution of the program
        log.debug(" \n \n ");
        log.debug("*************** Before Method Execution *************** \n {}. {} () with arguments[s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                Arrays.toString(jp.getArgs()));
        log.debug("_______________________________________________________ \n ");

        Object o = null;
        try {
            o = jp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        log.debug(" \n ");
        log.debug("*************** After Method Execution *************** \n {}. {} () with arguments[s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                Arrays.toString(jp.getArgs()));
        log.debug("______________________________________________________ \n \n ");

        return o;
    }
}
