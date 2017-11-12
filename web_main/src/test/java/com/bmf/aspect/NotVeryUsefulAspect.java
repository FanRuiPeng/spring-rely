package com.bmf.aspect;

import org.aspectj.lang.annotation.*;

@Aspect
public class NotVeryUsefulAspect {

    @Pointcut("execution(* transfer(..))")
    private void anyOldTransfer() {

    }
}
