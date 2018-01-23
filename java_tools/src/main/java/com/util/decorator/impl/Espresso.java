package com.util.decorator.impl;

import com.util.decorator.func.Beverage;

public class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    @Override
    public int cost() {
        return 30;
    }
}
