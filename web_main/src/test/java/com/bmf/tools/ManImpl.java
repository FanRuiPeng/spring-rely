package com.bmf.tools;

import com.bmf.func.ManInterface;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ManImpl implements ManInterface {
    @Override
    public Man getMan() {
        return new Man().setFirstName("张").setLastName("三");
    }
}
