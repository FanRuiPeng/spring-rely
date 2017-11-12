package com.bmf.aspect;

import com.bmf.func.TestBeanInterface;
import com.bmf.tools.AspectAction;
import com.bmf.tools.Child;
import com.bmf.tools.ManImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestBeanInterface
public class AspectTest {

    @Autowired
    private Child child;

    @Autowired
    private ManImpl manImpl;

    @Autowired
    private AspectAction aspectAction;

    @Test
    public void test() {
        manImpl.getMan();

//        aspectAction.doSomething();
    }
}
