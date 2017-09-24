package com.util.transient_test;

public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
//        new ReaderThread().start();
//        number = 42;
//        ready = true;
//        System.out.println(3<< 13);
//        long sum = 0l;
//        long start = System.currentTimeMillis();
//        for (long i = 0; i < Integer.MAX_VALUE; i++) {
//            sum += i;
//        }
//        System.out.println(System.currentTimeMillis() - start);
//        Integer a = 1;
//        Integer b = Integer.valueOf(1);
//        Integer c = new Integer(1);
//        int d = 1;
//        System.out.println(a == b);//true
//        System.out.println(a == c);//false
//        System.out.println(a == d);//true
//        System.out.println(b == c);//false
//        System.out.println(b == d);//true
//        System.out.println(c == d);//true
//        Integer a = 129;
//        Integer b = Integer.valueOf(129);
//        Integer c = new Integer(129);
//        int d = 129;
//        System.out.println(a == b);//false
//        System.out.println(a == c);//false
//        System.out.println(a == d);//true
//        System.out.println(b == c);//false
//        System.out.println(b == d);//true
//        System.out.println(c == d);//true
        String a = "java";
        String b = new String("java");

    }
}
