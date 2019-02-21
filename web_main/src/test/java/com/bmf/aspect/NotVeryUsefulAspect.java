package com.bmf.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class NotVeryUsefulAspect {

    @Pointcut("execution(* transfer(..))")
    private void anyOldTransfer() {

    }
}
