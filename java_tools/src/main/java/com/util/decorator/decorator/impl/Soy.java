package com.util.decorator.decorator.impl;

import com.util.decorator.decorator.func.CondimentDecorator;
import com.util.decorator.func.Beverage;

public class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    public int getSize() {
        return beverage.getSize();
    }

    @Override
    public int cost() {
        int cost = beverage.cost();
        if (getSize() == Beverage.TALL) {
            cost += 1;
        } else if (getSize() == Beverage.GRANDE) {
            cost += 2;
        } else if (getSize() == Beverage.VENTI) {
            cost += 3;
        }
        return cost;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }
}
