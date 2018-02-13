package com.bmf.test;

import com.bmf.task.SampleBeanInitializer;
import com.bmf.task.TaskExecutorExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration("/config/task.xml")
@RunWith(SpringRunner.class)
public class TaskTest {

    @Autowired
    private TaskExecutorExample taskExecutorExample;

    @Autowired
    private SampleBeanInitializer initializer;

    @Test
    public void test() {
//        taskExecutorExample.printMessages();
//        initializer.initialize();

    }
}
