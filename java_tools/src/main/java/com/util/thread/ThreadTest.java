package com.util.thread;

public class ThreadTest {

    private static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
//        for (int i = 0 ; i< 10; i++) {
//            new Run().run();
//        }

        ThreadA t1 = new ThreadA("t1");
//        ThreadA t2 = new ThreadA("t2");
        t1.start();
        t1.join();
        System.out.println("main is stoped");
//        t2.start();
    }

    static class ThreadA extends Thread{
        public ThreadA(String name){
            super(name);
        }
        public void run(){
            // 获取obj对象的同步锁
            synchronized (obj) {
                try {
                    for(int i=0; i <10; i++){
                        System.out.printf("%s: %d\n", this.getName(), i);
                        // i能被4整除时，休眠100毫秒
                        if (i%4 == 0)
                            Thread.yield();
                            Thread.sleep(100);
                    }
                    System.out.println("child is stop");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
