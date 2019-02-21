package com.bmf.seckill;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class StopThread {

//    private static volatile boolean stopRequested;
    private static AtomicBoolean stopRequested = new AtomicBoolean(false);

//    private static synchronized void requestStop() {
//        stopRequested = true;
//    }
//
//    private static synchronized boolean stopRequested() {
//        return stopRequested;
//    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stopRequested.get()) {
                i++;
//                System.out.println(stopRequested + " : " + i);
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
//        stopRequested = true;
        stopRequested.set(true);
    }
}
