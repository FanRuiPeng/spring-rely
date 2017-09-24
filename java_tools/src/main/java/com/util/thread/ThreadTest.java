package com.util.thread;

public class ThreadTest {

    public static void main(String[] args) {
        for (int i = 0 ; i< 10; i++) {
            new Run().run();
        }
    }
}

class Run implements Runnable {

    @Override
    public void run() {
        String a = "1";
        String b = new String("1");
        System.out.println(a.intern() == b.intern());
//        synchronized (Main.class) {
            try {
                System.out.println(Thread.currentThread().getName() + " :started");
                Thread.sleep(3000);
//                Thread.yield();
                System.out.println(Thread.currentThread().getName() + " :stop");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        }
    }
}

class Main {

}
