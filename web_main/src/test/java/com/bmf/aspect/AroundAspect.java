package com.bmf.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AroundAspect {

    @Around("execution(* com.bmf.tools.*.*(..))" +
            "&& within(com.bmf.service.*.*)")
    public Object doBasicProfiling(ProceedingJoinPoint point) throws Throwable {
        Object retVal = point.proceed();
        return retVal;
    }
}
