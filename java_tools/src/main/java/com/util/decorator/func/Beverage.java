package com.util.decorator.func;

public abstract class Beverage {

    public final static int TALL = 1;
    public final static int GRANDE = 2;
    public final static int VENTI = 3;

    protected String description = "default description";
    protected int size = TALL;

    public int getSize() {
        return size;
    }

    public Beverage setSize(int size) {
        this.size = size;
        return this;
    }

    public String getDescription() {
        return description;
    }

//    public Beverage setDescription(String description) {
//        this.description = description;
//        return this;
//    }

    public abstract int cost();

}
