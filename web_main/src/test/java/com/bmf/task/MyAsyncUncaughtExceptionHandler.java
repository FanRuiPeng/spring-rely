package com.bmf.task;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        System.out.println("method [ " + method.getName() + " ] with param [ " + Arrays.toString(params) + " ] throw exception caused by " + ex.getMessage());
    }
}
