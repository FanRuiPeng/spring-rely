package com.bmf.convert;


/**
 * Created by BMF on 2017/8/12.
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
//        int i = 0;
//        while (true) {
//            System.out.println(i);
//            list.add(String.valueOf(i++).intern());
        String str2 = new StringBuilder("Ja").append("va").toString();
        System.out.println(str2.intern() == str2);
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
    }
}
