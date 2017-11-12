package com.bmf.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectExpression {

//    @Pointcut("execution(public * com.bmf.tools.*.*(..))")
//    public void execution() {
////        System.out.println("execution");
//    }

    @Before("com.bmf.aspect.AspectExpression.thisTest()")
    public void before() {
        System.out.println("before");
    }

    //
//    /**
//     * .如果传的是全类名(包名.类名):匹配此类下所有的方法
//     * .如果传的时包名:匹配此包下所有类的方法
//     */
//    @Pointcut("within(com.bmf.tools..*)")
//    //匹配同包下该类的所有方法
//    @Pointcut("within(com.bmf..*)")
//    public void within() {
//        System.out.println("within");
//    }
//
    /**
     * 匹配代理对象和普通对象及其所有子类的方法
     */
    @Pointcut("this(com.bmf.func.ManInterface)")
    public void thisTest() {
        System.out.println("this");
    }
//
//    /**
//     * 匹配目标对象和普通对象及其所有子类的方法
//     */
//    @Pointcut("target(com.bmf.service.JdbcService)")
//    public void target() {
//
//    }
//
//    /**
//     * 匹配spring容器所有此参数类型和列表的方法(Long)
//     */
////    @Pointcut("args(Long)")
////    @Pointcut("args(Long,String)")
//    //匹配spring容器所有此参数类型和列表的方法(第一个为Long,后面随意)
//    @Pointcut("args(Long,..)")
//    public void args() {
//
//    }
//
//    @Pointcut("@target()")
//    public void targetAnnotation() {
//
//    }
//
//    /**
//     *
//     */
//    @Pointcut("@args()")
//    public void argsAnnotation() {
//
//    }
//
//    /**
//     * 参数注解匹配 匹配所有使用了Autowired注解了参数的方法
//     */
//    @Pointcut("@within(org.springframework.beans.factory.annotation.Autowired)")
//    public void withinAnnotation() {
//
//    }
//
//    /**
//     * 方法注解匹配,匹配所有带Service注解的方法
//     */
//    @Pointcut("@annotation(org.springframework.stereotype.Service)")
//    public void annotationAnnotation() {
//
//    }
//
//    /**
//     * 匹配bean的名字
//     */
//    @Pointcut("bean(*service) || bean(Redis*)")
//    public void bean() {
//
//    }
}
