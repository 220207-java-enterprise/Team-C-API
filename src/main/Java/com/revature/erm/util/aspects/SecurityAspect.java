package com.revature.erm.util.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;

public class SecurityAspect {

    @Before("") // what will the pointcut to target methods annotated with @Secured look like?
    public void secureEndpoint(JoinPoint jp) {
        // what security logic do we want to run here?
    }
}
