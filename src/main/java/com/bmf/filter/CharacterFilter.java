package com.bmf.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BMF on 2017/6/4.
 */
public class CharacterFilter implements Filter {
    private String character = "UTF-8";
//    private static Logger logger = LoggerFactory.getLogger(CharacterFilter.class);
    org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CharacterFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encoding = filterConfig.getInitParameter("encoding");
        if (null != encoding && encoding.trim() != "") {
            character = encoding;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setCharacterEncoding("UTF-8");
        String header = response.getHeader("Content-Type");
        String encoding = response.getCharacterEncoding();
//        MDC.put("key", request.getRequestURL().toString());
        filterChain.doFilter(servletRequest, servletResponse);
        response.setCharacterEncoding("UTF-8");
        header = response.getHeader("Content-Type");
        encoding = response.getCharacterEncoding();
        log.info("header is "+  header);
//        logger.info(header);
//        logger.info("response encoding: ", encoding);
//        if (response.getStatus() >= 400 && response.getStatus() < 500) {
////            response.sendRedirect("/sv1/page/index");
//            request.getRequestDispatcher("/sv1/page/index").forward(servletRequest, servletResponse);
//        }
    }

    @Override
    public void destroy() {

    }
}
