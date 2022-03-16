package com.revature.erm.util.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.revature.erm..*)")
    public void logAll() {}

    @Before(value = "logAll()")
    public void logMethodStart(JoinPoint jp) {
        String methodSig = extractMethodSignature(jp);
        String methodArgs = Arrays.toString(jp.getArgs());
        logger.info("{} invoked at {} with the provided arguments: {}", methodSig, LocalDateTime.now(), methodArgs);
    }

    @AfterReturning(value = "logAll()", returning="returnedObj")
    public void logMethodReturn(JoinPoint jp, Object returnedObj) {
        String methodSig = extractMethodSignature(jp);
        logger.info("{} successfully returned at {} with value: {}", methodSig, LocalDateTime.now(), returnedObj);
    }

    @AfterThrowing(value = "logAll()", throwing = "t")
    public void logMethodException(JoinPoint jp, Throwable t) {
        String methodSig = extractMethodSignature(jp);
        String exceptionName = t.getClass().getName();
        logger.warn("{} was thrown in method {} at {} with message: {}", exceptionName, methodSig, LocalDateTime.now(), t.getMessage());
    }

    private String extractMethodSignature(JoinPoint jp) {
        return jp.getTarget().getClass().toString() + "#" + jp.getSignature().getName();

    }


}
