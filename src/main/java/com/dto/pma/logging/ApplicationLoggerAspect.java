package com.dto.pma.logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.dto.pma.controllers..*)" +
            "|| within(com.dto.pma.dao..*)") // where logger is applied
    public void definePackagePointcuts() {
        // empty method just to name the location specified in the pointcut
    }

    @Before("definePackagePointcuts()") // After=log shows after execution, Before=before execution
    public void log() {
        log.debug("---------------------------------------------");
    }
}
