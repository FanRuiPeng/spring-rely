package com.util.decorator.impl;

import com.util.decorator.func.Beverage;

public class DorkRoast extends Beverage {
    public DorkRoast() {
        description = "DorkRoast";
    }

    @Override
    public int cost() {
        return 20;
    }
}
