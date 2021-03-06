package com.bmf.tools;

import com.bmf.func.TestBeanInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

/**
 * RedisTool Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>九月 23, 2017</pre>
 */
//@ContextConfiguration("../conf/spring-test.xml")
//@DirtiesContext
@RunWith(SpringRunner.class)
//@TestPropertySource(properties = {"a = 1", "b = 2"})
@TestBeanInterface
public class RedisToolTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {
    }

    @Autowired
    private Child child;

    /**
     * Method: put(String key, String value, Long time)
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testPut() throws Exception {
//TODO: ReentrantLockTest goes here...

//        child.get();
        System.out.println("----------------"+ child.getResource().getFilename());
        MessageSource messageSource = new ClassPathXmlApplicationContext("config/spring-test.xml");
        String message = messageSource.getMessage("show.name", new Object[]{"张三"}, "required", Locale.CHINA);
        System.out.println(message);
        message = messageSource.getMessage("show.name", null, "required", Locale.ENGLISH);
        System.out.println(message);

//        Properties properties = System.getProperties();
//        Enumeration<Object> elements = properties.elements();
//        while (elements.hasMoreElements()) {
//            System.out.println(elements.nextElement());
//        }
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        for (String str : beanDefinitionNames) {
//            System.out.println(str);
//        }
//        BoundHashOperations test = redisTemplate.boundHashOps("test");
////        test.put(1, 1);
//        Object o = test.get(1);
//        System.out.println(o);
    }

    /**
     * Method: get(String a)
     */
    @Test
    public void testGet() throws Exception {
//TODO: ReentrantLockTest goes here...
    }

    /**
     * Method: getKey()
     */
    @Test
    public void testGetKey() throws Exception {
//TODO: ReentrantLockTest goes here...
    }

    /**
     * Method: setKey(String key)
     */
    @Test
    public void testSetKey() throws Exception {
//TODO: ReentrantLockTest goes here...
    }

    /**
     * Method: getValue()
     */
    @Test
    public void testGetValue() throws Exception {
//TODO: ReentrantLockTest goes here...
    }

    /**
     * Method: setValue(Object value)
     */
    @Test
    public void testSetValue() throws Exception {
//TODO: ReentrantLockTest goes here...
    }

    /**
     * Method: equals(Object o)
     */
    @Test
    public void testEquals() throws Exception {
//TODO: ReentrantLockTest goes here...
    }

    /**
     * Method: hashCode()
     */
    @Test
    public void testHashCode() throws Exception {
//TODO: ReentrantLockTest goes here...
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
//TODO: ReentrantLockTest goes here...
    }

    /**
     * Method: execute(RedisOperations operations)
     */
    @Test
    public void testExecuteOperations() throws Exception {
//TODO: ReentrantLockTest goes here...
    }


} 
