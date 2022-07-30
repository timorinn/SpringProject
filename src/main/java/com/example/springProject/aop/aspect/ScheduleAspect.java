package com.example.springProject.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ScheduleAspect {

    @Pointcut("@annotation(com.example.springProject.annotations.Loggable)")
    public void logMethod() {}

    @Before("logMethod()")
    public void beforeMethod() {
        log.info("LONG SCHEDULE STARTING HIS JOB !!! ");
    }
}
