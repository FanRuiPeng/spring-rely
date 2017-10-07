package com.bmf.tools;

public class Child extends Parent {
    @Override
    public void get() {
        System.out.println(this.getSingle());
        System.out.println(this.getTo());
    }
}
