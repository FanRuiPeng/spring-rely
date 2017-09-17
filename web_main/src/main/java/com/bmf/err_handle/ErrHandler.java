package com.bmf.err_handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by BMF on 2017/8/24.
 */
@ControllerAdvice
public class ErrHandler {

    private final static Logger log = LoggerFactory.getLogger(ErrHandler.class);

    @ExceptionHandler(Exception.class)
    public String handlerException(HttpServletRequest request, Exception e) {
        StringBuffer url = request.getRequestURL();
        log.error("error url ============> " + url);
        e.printStackTrace();
        log.error("error caused by :({}-{})", e.getMessage(), e.getStackTrace());
        if (e instanceof NoHandlerFoundException) {
            return "err_page/page_404";
        }
        return "err_page/page_500";
    }
}
