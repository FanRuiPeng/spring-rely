package com.bmf.server;

import com.bmf.model.base.DataWrapper;
import com.bmf.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by BMF on 2017/8/20.
 */
@Controller
@RequestMapping(value = "/sv1/jdbc")
public class JdbcController {

    //    @Autowired
//    @Qualifier("jdbc")
    @Resource
    private JdbcService jdbcService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public DataWrapper<Object> test() {
        return jdbcService.test();
//        jdbcService.updateJdbc();
//        return null;
    }

    @RequestMapping(value = "/date")
    @ResponseBody
    public DataWrapper<Object> date(
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Date date) {
        System.out.println(date);
        return new DataWrapper<Object>().setData(date);
    }

    @RequestMapping(value = "/testName")
    @ResponseBody
    public DataWrapper<Object> testName() {
        return jdbcService.testName();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> haddlerException(HttpServletRequest request, Exception e) {
       StringBuffer url = request.getRequestURL();
        System.out.println("error url ============> " + url);
        e.printStackTrace();
        return new ResponseEntity<String>("something error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

