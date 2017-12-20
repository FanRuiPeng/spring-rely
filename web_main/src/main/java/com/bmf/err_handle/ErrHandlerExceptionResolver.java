package com.bmf.err_handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrHandlerExceptionResolver implements HandlerExceptionResolver {
    private final static Logger log = LoggerFactory.getLogger(ErrHandlerExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        ModelAndView modelAndView = new ModelAndView("err_page/page_500");
        StringBuffer url = request.getRequestURL();
        log.error("error url ============> " + url);
        e.printStackTrace();
        log.error("error caused by :({}-{})", e.getMessage(), e.getStackTrace());
        if (e instanceof NoHandlerFoundException) {
            modelAndView.setViewName("err_page/page_404");
        }
        return modelAndView;
    }
}
