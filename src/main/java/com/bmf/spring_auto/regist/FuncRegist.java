package com.bmf.spring_auto.regist;

import com.bmf.spring_auto.func.Func;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by BMF on 2017/8/23.
 */
@Service
public class FuncRegist implements ApplicationContextAware {

    private final static Logger log = LoggerFactory.getLogger(FuncRegist.class);

    private Map<String, Func> funcMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        funcMap = applicationContext.getBeansOfType(Func.class);
    }

    public Func getFunc(String funcName) {
        return funcMap.get(funcName);
    }
}
