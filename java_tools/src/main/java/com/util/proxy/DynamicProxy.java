package com.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by BMF on 2017/8/22.
 */
public class DynamicProxy implements InvocationHandler {
    private Object taget;

    public DynamicProxy(Object taget) {
        this.taget = taget;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                taget.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理方法开始执行");
        Object invoke = method.invoke(taget, args);
        System.out.println("代理方法执行结束");
        return invoke;
    }
}
