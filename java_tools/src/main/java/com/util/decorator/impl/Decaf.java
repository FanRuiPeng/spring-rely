package com.util.decorator.impl;

import com.util.decorator.func.Beverage;

public class Decaf extends Beverage {
    public Decaf() {
        description = "Decaf";
    }

    @Override
    public int cost() {
        return 40;
    }
}
