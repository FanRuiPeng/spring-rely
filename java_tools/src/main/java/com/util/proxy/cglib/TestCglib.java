package com.util.proxy.cglib;

import com.util.proxy.Animal;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * Created by BMF on 2018/2/12.
 */
public class TestCglib {

    public static void main(String[] args) {
//        obj();
//        interfaces();
        LazyBean bean = new LazyBean("张三", 66);
        int age = bean.getAge();
//        System.out.println(age);
        PropertyBean propertyBean = bean.getPropertyBean();
        PropertyBean propertyBeanDispatcher = bean.getPropertyBeanDispatcher();
        String key = propertyBean.getKey();
        String key1 = propertyBeanDispatcher.getKey();
        propertyBean.getKey();
        propertyBeanDispatcher.getKey();
    }

    private static void obj() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);

        //方法过滤器
        TargetMethodCallbackFilter targetMethodCallbackFilter = new TargetMethodCallbackFilter();

        //方法拦截器
        TargetInterceptor targetInterceptor = new TargetInterceptor();

        //NoOp.INSTANCE：这个NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
        NoOp instance = NoOp.INSTANCE;

        //FixedValue：表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
        TargetResultFixed targetResultFixed = new TargetResultFixed();

        Callback[] callbacks = {instance, targetInterceptor, targetResultFixed};

//        enhancer.setCallback(targetInterceptor);
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(targetMethodCallbackFilter);
        TargetObject targetObject = (TargetObject) enhancer.create();
        System.out.println(targetObject.method1("mm1"));
        System.out.println(targetObject.method2(200));
        System.out.println(targetObject.method3(300));
        System.out.println(targetObject.method3(200));
    }

    private static void interfaces() {
        Enhancer enhancer = new Enhancer();
        Class[] classes = {Animal.class};
        enhancer.setInterfaces(classes);
        enhancer.setCallback(new TargetInterceptor());
        Animal animal = (Animal) enhancer.create();
        animal.eat();
        animal.run();
    }
}
