package com.bmf.listener;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        String log4jRealPath = servletContext.getRealPath(log4jConfigLocation);
        try (InputStream inputStream = new FileInputStream(new File(log4jRealPath))) {
            PropertyConfigurator.configure(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.warn(">>>>>>>>>>>>>>>>>> log4j config not found");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(">>>>>>>>>>>>>>>>>>>log4j config load error");
        }
        String servletRealPath = servletContext.getRealPath("");
        logger.info("****************servletRealPath:[{}]",servletRealPath);

        super.contextInitialized(event);
    }
}
