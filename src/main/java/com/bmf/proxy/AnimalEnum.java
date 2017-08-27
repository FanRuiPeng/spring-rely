package com.bmf.proxy;

/**
 * Created by BMF on 2017/8/22.
 */
public enum AnimalEnum {
    dog(new Dog()),
    cat(new Cat());

    private final Animal animal;

    AnimalEnum(Animal animal) {
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }
}
