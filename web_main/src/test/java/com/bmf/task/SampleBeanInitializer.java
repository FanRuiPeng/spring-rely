package com.bmf.task;

import javax.annotation.PostConstruct;

public class SampleBeanInitializer {

    private final SampleBeanImpl bean;

    public SampleBeanInitializer(SampleBeanImpl bean) {
        this.bean = bean;
    }

    @PostConstruct
    public void initialize() {
        bean.doSomething();
        System.out.println(1);
    }

}