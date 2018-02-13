package com.util.nested;

public class NestedTest {

    public static void main(String[] args) {
        //创建静态内部类
        OuterClass.StaticNestedClass staticNestedClass = new OuterClass.StaticNestedClass();
        OuterClass outerClass = new OuterClass();

        //创建内部类
        OuterClass.NestedClass nestedClass = outerClass.new NestedClass();
    }
}
