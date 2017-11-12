package com.bmf.func;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration("/config/spring-test.xml")
//@ActiveProfiles("dev")
//@EnableAspectJAutoProxy
public @interface TestBeanInterface {
}
