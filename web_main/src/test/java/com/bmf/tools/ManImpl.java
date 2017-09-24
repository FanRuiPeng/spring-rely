package com.bmf.tools;

import com.bmf.func.ManInterface;

public class ManImpl implements ManInterface {
    @Override
    public Man getMan() {
        return new Man().setFirstName("张").setLastName("三");
    }
}
