package com.bmf.task;

import org.springframework.scheduling.annotation.Async;

public class SampleBeanImpl {

    @Async("threadPoolTaskExecutor")
    void doSomething() {
        System.out.println("----------start-----------");
        // ...
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----------end-----------");
    }

}
