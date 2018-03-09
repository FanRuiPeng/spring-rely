package com.bmf.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by BMF on 2018/3/9.
 */
@Controller
@RequestMapping("/sv1/aliyun")
public class AliyunApi {

    @RequestMapping("/upload")
    public String upload() {
        return "aliyun";
    }
}
