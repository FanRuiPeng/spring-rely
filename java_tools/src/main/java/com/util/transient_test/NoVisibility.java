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
//        String a = "java";
//        String b = new String("java");
        System.out.println(-1L >>> -1024L);


//        boolean flag = true;
//        flag &= true;
//        System.out.println("true\t&=\ttrue\t==>\t" + flag);
//        flag = true;
//        flag &= false;
//        System.out.println("true\t&=\tfalse\t==>\t" + flag);
//        flag = false;
//        flag &= true;
//        System.out.println("false\t&=\ttrue\t==>\t" + flag);
//        flag = false;
//        flag &= false;
//        System.out.println("false\t&=\tfalse\t==>\t" + flag+"\n");
//
//        flag = true;
//        flag |= true;
//        System.out.println("true\t|=\ttrue\t==>\t" + flag);
//        flag = true;
//        flag |= false;
//        System.out.println("true\t|=\tfalse\t==>\t" + flag);
//        flag = false;
//        flag |= true;
//        System.out.println("false\t|=\ttrue\t==>\t" + flag);
//        flag = false;
//        flag |= false;
//        System.out.println("false\t|=\tfalse\t==>\t" + flag+"\n");
//
//        System.out.println("^=  相同为真，不同为假");
//        flag = true;
//        flag ^= true;
//        System.out.println("true\t^=\ttrue\t==>\t" + flag);
//        flag = true;
//        flag ^= false;
//        System.out.println("true\t^=\tfalse\t==>\t" + flag);
//        flag = false;
//        flag ^= true;
//        System.out.println("false\t^=\ttrue\t==>\t" + flag);
//        flag = false;
//        flag ^= false;
//        System.out.println("false\t^=\tfalse\t==>\t" + flag);


    }
}
