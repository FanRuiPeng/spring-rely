package com.util.decorator.impl;

import com.util.decorator.func.Beverage;

public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "HouseBlend";
    }

    @Override
    public int cost() {
        return 10;
    }
}
