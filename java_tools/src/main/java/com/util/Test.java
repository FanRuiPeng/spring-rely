package com.util;

import java.util.LinkedList;
import java.util.List;

public strictfp class Test {

    public strictfp static void main(String[] args) {
//        String a = "12121212121212121211111222";
//        System.out.println(String.join("-", a.split("1", 12)));

//        float a = 1F;
//        float b = 0.9F;
//        System.out.println(a - b);

//        ex(2);


//        LinkedList<List<String>> a = new LinkedList<>();
//        List<String> b = new LinkedList<>();
//        b.add("b");
//        a.add(b);
//        b.add("a");
//        System.out.println(a);
//        System.out.println(b);

        Father father = new Son();
        char c = 'a';
        father.f1(c);

    }

    public static Integer ex(int a) {
        try {
            if (a == 2) {
                throw new Exception("yichang");
            }
            System.out.println("return1");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }
        System.out.println("return2");
        return 2;
    }
}

class Father {
    public void f1() {
        System.out.println("father-f1()");
    }

    public void f1(int i) {
        System.out.println("father-f1() para-int " + i);
    }
}

class Son extends Father {
    public void f1() {
        System.out.println("son-f1()");
    }

    public void f1(char c) {
        System.out.println("son-s1() para-char " + c);
    }
}
