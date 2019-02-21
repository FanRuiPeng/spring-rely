package com.util.decorator;

import com.util.decorator.func.Beverage;
import com.util.decorator.impl.Espresso;

public class Test {

    public static void main(String args[]) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());
//
//        Beverage beverage1 = new DorkRoast();
//        beverage1 = new Mocha(beverage1);
//        beverage1 = new Mocha(beverage1);
//        beverage1 = new Whip(beverage1);
//        System.out.println(beverage1.getDescription() + " $ " + beverage1.cost());
    }
}
