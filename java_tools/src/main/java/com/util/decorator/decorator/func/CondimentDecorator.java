package com.util.decorator.decorator.func;

import com.util.decorator.func.Beverage;

public abstract class CondimentDecorator extends Beverage {
    @Override
    public abstract String getDescription();
}
