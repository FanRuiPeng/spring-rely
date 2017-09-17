package com.bmf.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by BMF on 2017/8/24.
 */
@Controller
@RequestMapping(name = "异常处理", value = "/sv1/err")
public class ErrorApi {

    @RequestMapping(value = "/500")
    public String errPage() {
        return "err_page/page_500";
    }
}
