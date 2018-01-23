package com.util.decorator.decorator.impl;

import com.util.decorator.decorator.func.CondimentDecorator;
import com.util.decorator.func.Beverage;

public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public int cost() {
        return 2 + beverage.cost();
    }
}
