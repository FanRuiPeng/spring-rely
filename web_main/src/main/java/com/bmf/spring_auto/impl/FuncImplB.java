package com.bmf.spring_auto.impl;

import com.bmf.spring_auto.func.Func;
import org.springframework.stereotype.Service;

/**
 * Created by BMF on 2017/8/23.
 */
@Service("funcB")
public class FuncImplB implements Func {
    @Override
    public void work() {
        System.out.println("B impl");
    }
}
