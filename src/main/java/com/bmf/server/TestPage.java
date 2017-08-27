package com.bmf.server;

import com.bmf.spring_auto.func.Func;
import com.bmf.spring_auto.regist.FuncRegist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by BMF on 2017/8/11.
 */
@RequestMapping(value = "/sv1/page")
@Controller
public class TestPage {

    @Autowired
    private FuncRegist funcRegist;

    @RequestMapping(value = "/index")
    public String index(String func, HttpServletRequest request) {
        Func funcImpl = funcRegist.getFunc(func);
        if (null == funcImpl) {
            return "err_page/page_500";
        } else {
            request.setAttribute("func", func);
        }
//        Func funA = funcRegist.getFunc("funcA");
//        funA.work();
//        Func funB = funcRegist.getFunc("funcB");
//        funB.work();
        return "forward:/WEB-INF/index.jsp";
    }
}
