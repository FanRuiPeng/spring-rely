package com.bmf.listener;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by BMF on 2017/8/20.
 */
public class CommonListener extends ContextLoaderListener {

    private final static Logger logger = LoggerFactory.getLogger(CommonListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.info("********************************content init****************************************************");
        ServletContext servletContext = event.getServletContext();
        String log4jConfigLocation = servletContext.getInitParameter("log4jConfigLocation");
//        String log4jRealPath = servletContext.getRealPath(log4jConfigLocation);
//        if (log4jConfigLocation.startsWith("classpath")) {
//            try {
//                File file = ResourceUtils.getFile(log4jConfigLocation);
//                System.out.println(file.getPath());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            log4jRealPath = Thread.currentThread().getContextClassLoader().getResource("/").getPath() + log4jConfigLocation.split(":")[1];
//        }
        try (InputStream inputStream = new FileInputStream(ResourceUtils.getFile(log4jConfigLocation))) {
            PropertyConfigurator.configure(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.warn(">>>>>>>>>>>>>>>>>> log4j config not found");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(">>>>>>>>>>>>>>>>>>>log4j config load error");
        }
        String servletRealPath = servletContext.getRealPath("");
        logger.info("****************servletRealPath:[{}]", servletRealPath);

        super.contextInitialized(event);
    }
}
