package com.bmf.aspect;

import org.aspectj.lang.annotation.*;

@Aspect
public class SingleAspect {
    @Before("within(com.bmf.service)")
    public void doBefore() {

    }

    @AfterReturning(value = "execution(* com.bmf.aspect.*.*(..))", returning = "retVal")
    public void doAccessCheck(Object retVal) {
    }

    @AfterThrowing(value = "within(com.bmf.service.JdbcService)", throwing = "e")
    public void doRecoveryActions(Exception e) {

    }

    /**
     * 在符合的方法执行后执行 无论正常执行还是异常退出  一般用户释放资源
     */
    @After("execution(* com.bmf.service.*.*(..))")
    public void doReleaseLock() {

    }
}
