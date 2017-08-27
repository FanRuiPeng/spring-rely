package com.bmf.proxy;

/**
 * Created by BMF on 2017/8/22.
 */
public class TestProxy {

    public static void main(String[] args) {
        AnimalProxy animalProxy = new AnimalProxy(AnimalEnum.valueOf("dog").getAnimal());
        Animal proxy = (Animal) animalProxy.getProxy();
        proxy.eat();
        proxy.run();
    }
}
