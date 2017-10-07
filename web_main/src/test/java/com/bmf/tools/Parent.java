package com.bmf.tools;

public abstract class Parent {
    private String single;
    private String to;

    public String getTo() {
        return to;
    }

    public Parent setTo(String to) {
        this.to = to;
        return this;
    }

    public String getSingle() {
        return single;
    }

    public Parent setSingle(String single) {
        this.single = single;
        return this;
    }

    public abstract void get();
}
