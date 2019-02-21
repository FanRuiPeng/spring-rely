package com.util.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by BMF on 2017/8/22.
 */
public class TestProxy {

    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy(AnimalEnum.valueOf("dog").getAnimal());
        Animal proxy = (Animal) dynamicProxy.getProxy();
        proxy.eat();
        proxy.run();

//        Class<Animal>[] classes = new Class[]{Animal.class};
//        Object instance = Proxy.newProxyInstance(TestProxy.class.getClassLoader(), classes, new InnerHandler(AnimalEnum.dog.getAnimal()));
//        Animal animal = (Animal) instance;
////        System.out.println(instance);
//        animal.run();
//        animal.eat();

//        Class[] classes = {BaseSum.class};
//        Object o = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), classes, new SumHandler(new SumImpl(1)));
//        BaseSum o1 = (BaseSum) o;
//        int show = o1.sum(2).sum(3).show();
//        System.out.println(show);

    }

}

class InnerHandler implements InvocationHandler {
    private Animal invoke;

    public InnerHandler(Animal invoke) {
        this.invoke = invoke;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getName());
        byte[] b = ProxyGenerator.generateProxyClass(proxy.getClass().getSimpleName(), proxy.getClass().getInterfaces());
        FileOutputStream out = new FileOutputStream("C:\\Users\\B\\Desktop\\" + proxy.getClass().getSimpleName() + ".class");
        out.write(b);
        out.flush();
        out.close();
        System.out.println("before invoke ");
        Object invoke = method.invoke(this.invoke, args);
        System.out.println("after invoke ");
        return invoke;
    }
}

class SumHandler implements InvocationHandler {
    private BaseSum seed;

    public SumHandler(BaseSum seed) {
        this.seed = seed;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        return method.invoke(seed, args);
    }
}

interface BaseSum {
    BaseSum sum(int inc);

    int show();
}

class SumImpl implements BaseSum {
    public SumImpl(int seed) {
        this.seed = seed;
    }

    private int seed;

    @Override
    public BaseSum sum(int inc) {
        seed += inc;
        return this;
    }

    @Override
    public int show() {
        return seed;
    }
}
