package com.bmf.tools;

import com.bmf.func.ManInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class DefaultConfigTest {

    @Configuration
    static class Config {

        @Bean
        public ManInterface manInterface() {
            ManInterface manInterface = new ManImpl();
            return manInterface;
        }
    }


    @Autowired
    private ManInterface manInterface;

    @Test
    public void testPut() throws Exception {
//TODO: ReentrantLockTest goes here...
        System.out.println("--------------" + manInterface.getMan());
    }
}
