package com.bmf.tools;

import com.bmf.func.TestWebInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;

@RunWith(SpringRunner.class)
@TestWebInterface
public class WebAppTest {
    @Autowired
    WebApplicationContext wac; // cached
//
//    @Autowired
//    MockServletContext servletContext; // cached
//
//    @Autowired
//    MockHttpSession session;
//
//    @Autowired
//    MockHttpServletRequest request;
//
//    @Autowired
//    MockHttpServletResponse response;
//
//    @Autowired
//    ServletWebRequest webRequest;

    @Test
    public void test() {
        String[] beanDefinitionNames = wac.getBeanDefinitionNames();
        for (String str : beanDefinitionNames) {
            System.out.println(str);
        }
    }
}
