package com.bmf.server;

import com.bmf.model.base.DataWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;
import java.util.stream.Stream;

@Controller
@RequestMapping("/sv1/message")
public class MessageApi {

    private final static Logger log = LoggerFactory.getLogger(MessageApi.class);

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/test")
    @ResponseBody
    public Object test(Integer type) {
        Locale local = null;
        if(null == type || type == 0) {
            local = Locale.SIMPLIFIED_CHINESE;
        } else {
            local = Locale.ENGLISH;
        }
        Stream.of(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        String message = messageSource.getMessage("show.name", null, local);
        log.info(message);
        return new DataWrapper<String>().setData(message);
    }
}
