package test;

import com.bmf.tools.Man;
import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

public class InstantiatContainer {

    @Test
    public void test() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        new XmlBeanDefinitionReader(applicationContext).loadBeanDefinitions(new String[]{"classpath:/config/services.xml", "classpath:/config/daos.xml"});
        applicationContext.refresh();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("alie", new Man().setFirstName("1").setLastName("2"));
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
//                new String[]{"classpath:/config/services.xml", "classpath:/config/daos.xml"});
//        String[] definitionNames = applicationContext.getBeanDefinitionNames();
//        String[] definitionNames = applicationContext.getBeanDefinitionNames();
//        for (String str : definitionNames) {
//            System.out.println(str);
//            Object bean = applicationContext.getBean(str);
//            System.out.println(bean);
//        }
        float a = 1.4f;
        double b = 1.4;
        System.out.println(a == b);
        Integer integer = applicationContext.getBean("integer", Integer.TYPE);
        Integer fors = applicationContext.getBean("for", Integer.TYPE);
        System.out.println(integer);
        System.out.println(fors);

    }
}
