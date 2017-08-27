package com.bmf.server;

import com.bmf.model.DataWrapper;
import com.bmf.sevice.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by BMF on 2017/8/20.
 */
@Controller
@RequestMapping(value = "/sv1/jdbc")
public class JdbcController {

    @Autowired
    private JdbcService jdbcService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public DataWrapper<Object> test() {
        return jdbcService.updateTest();
    }
}

