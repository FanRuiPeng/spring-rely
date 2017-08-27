package com.bmf.proxy;

/**
 * Created by BMF on 2017/8/22.
 */
public interface Animal {
    default void run() {
        System.out.println(this.getClass().getName() + "正在跑");
    }

    void eat();
}

class Dog implements Animal {
    private static volatile Dog instance;

    public Dog() {

    }

    public static Dog getInstance() {
        if (instance == null) {
            synchronized (Dog.class) {
                if (instance == null) {
                    instance = new Dog();
                }
            }
        }
        return instance;
    }

    @Override
    public void run() {
        System.out.println("小狗正在跑");
    }

    @Override
    public void eat() {
        System.out.println("小狗在啃骨头");
    }
}

class Cat implements Animal {
    private static volatile Cat instance;

    public Cat() {

    }

    public static Cat getInstance() {
        if (instance == null) {
            synchronized (Cat.class) {
                if (instance == null) {
                    instance = new Cat();
                }
            }
        }
        return instance;
    }

    @Override
    public void run() {
        System.out.println("猫正在跑");
    }

    @Override
    public void eat() {
        System.out.println("猫在抓老鼠吃");
    }
}
