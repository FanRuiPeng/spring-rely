package com.util.extend;

public class Child extends Father {
    protected int age = 1;

    public Child() {
        System.out.println("child init");
    }

    public Child(int a) {
        this();
        System.out.println("child init" + a);
    }

    public static void main(String[] args) {
        Father f = new Child(1);
//        System.out.println(f instanceof Father);
//        System.out.println(f instanceof Child);
//        System.out.println(f.age);

        String a = "a";
        String b = new String("a");
        System.out.println(a == b.intern());

        Integer c = 127;
        Integer d = 127;
        System.out.println(c == d);
        System.out.println(Integer.parseInt("11") == Integer.valueOf(11));
        System.out.println(f.hashCode());
    }

    {
        System.out.println("child init  not static");
    }

    static {
        System.out.println("child init static");
    }
}
