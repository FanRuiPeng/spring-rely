package com.util.reflect;

import java.io.Console;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Retrieve {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<? extends String> aClass = "aaa".getClass();
        System.out.println(aClass);
//        Class<? extends Console> console = System.console().getClass();
//        System.out.println(console);

        Class c = E.A.getClass();
        System.out.println(c);

        byte[] bytes = new byte[1024];
        Class bytesClass = bytes.getClass();
        System.out.println(bytesClass);

        Set<String> s = new HashSet<String>();
        Class sClass = s.getClass();
        System.out.println(sClass);
        System.out.println(boolean.class);
        System.out.println(int[][][].class);

        Class<?> aClass1 = Class.forName("com.util.reflect.E");
        System.out.println(aClass1);
        Class<Void> type = Void.TYPE;
        Class<? super E> superclass = E.class.getSuperclass().getSuperclass().getSuperclass();
        System.out.println(superclass);

        Class<?>[] classes = Character.class.getDeclaredClasses();
        for (Class cl: classes) {
            System.out.println(cl);
        }
    }
}

enum E {A, B}
