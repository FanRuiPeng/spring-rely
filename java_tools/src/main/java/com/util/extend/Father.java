package com.util.extend;

public class Father {

    protected int age = 99;

    {
        System.out.println("father init not static");
    }

    static {
        System.out.println("father init static");
    }

    public Father() {
        System.out.println("father init");
    }
}
