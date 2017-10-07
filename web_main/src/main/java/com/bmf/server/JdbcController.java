package com.bmf.server;

import com.bmf.model.base.DataWrapper;
import com.bmf.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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

    @RequestMapping(value = "/testName")
    @ResponseBody
    public DataWrapper<Object> testName() {
        return jdbcService.testName();
    }
}

