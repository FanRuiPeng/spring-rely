package com.util;

import java.io.Serializable;

public strictfp class Test {

    private final static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    public strictfp static void main(String[] args) throws InstantiationException {
//        for (int i = 0; i < 5; i++) {
//            new Thread(() -> {
//                System.out.println("线程" + Thread.currentThread().getName() + "的初始value:" + threadLocal.get());
//                for (int j = 0; j < 10; j++) {
//                    threadLocal.set(threadLocal.get() + j);
//                }
//                System.out.println("线程" + Thread.currentThread().getName() + "的累加value:" + threadLocal.get());
//            }).start();
//        }
//
//        Unsafe unsafe = Unsafe.getUnsafe();
//        A a = (A) unsafe.allocateInstance(A.class);


//        ThreadLocal<String> stringThreadLocal1 = new ThreadLocal<>();
//        ThreadLocal<String> stringThreadLocal2 = new ThreadLocal<>();
//        stringThreadLocal1.set("a");
//        stringThreadLocal2.set("a");
//        String s1 = stringThreadLocal1.get();
//        String s2 = stringThreadLocal2.get();
//        System.out.println(s1 + " : " + s2);
//        stringThreadLocal1.set("b");
//        s1 = stringThreadLocal1.get();
//        System.out.println(s1 + " : " + s2);

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

//        Father father = new Son();
//        char c = 'a';
//        father.f1(c);
//
//        String aa = new String("aa");

//        String s = new String("1");
//        String s2 = "1";
//       s = s.intern();
//        System.out.println(s == s2);
//
//        String s3 = new String("1") + new String("1");
//        String s4 = "11";
//         s3 = s3.intern();
//        System.out.println(s3 == s4);

//        String a = "a";
//        String b = "b";
//        String c = a + b;
//        String d = "a" + b;
//        String e = "a" + "b";
//        String f = "ab";
//        String g = new String("ab");
//        System.out.println(c == d);//false
//        System.out.println(c == e);//false
//        System.out.println(d == e);//false
//        System.out.println(f == e);//true
//        System.out.println(f == c.intern());//false
//        System.out.println(f == d.intern());//false
//        System.out.println(f == g);//false

        int x = 97;
        int r = x / 10 * 9 + (x % 10) * 9 / 10;
        System.out.println(r);

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
    static {
        System.out.println("father static");
    }

    public Father() {
        System.out.println("father constrat");
    }

    public void f1() {
        System.out.println("father-f1()");
    }

    public void f1(int i) {
        System.out.println("father-f1() para-int " + i);
    }
}

class Son extends Father {
    static {
        System.out.println("son static");
    }

    public Son() {
        System.out.println("son constrat");
    }

    public void f1() {
        System.out.println("son-f1()");
    }

    public void f1(char c) {
        System.out.println("son-s1() para-char " + c);
    }
}

class A implements Serializable {
    private final int num;

    public A(int num) {
        System.out.println("init num");
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
