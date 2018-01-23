package com.util.decorator.decorator.impl;

import com.util.decorator.decorator.func.CondimentDecorator;
import com.util.decorator.func.Beverage;

public class Milk extends CondimentDecorator {
    Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }

    @Override
    public int cost() {
        return 1 + beverage.cost();
    }
}
