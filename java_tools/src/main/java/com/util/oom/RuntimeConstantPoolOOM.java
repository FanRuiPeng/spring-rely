package com.util.oom;

public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        String str4 = "计算机软件";
        System.out.println(str4.intern() == str4);
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str3 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str3.intern() == str3);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
