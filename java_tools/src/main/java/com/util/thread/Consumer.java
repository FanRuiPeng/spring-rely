package com.util.thread;

/**
 * Created by BMF on 2018/3/12.
 */
public class Consumer implements Runnable {
    SyncStack syncStack;

    public Consumer(SyncStack syncStack) {
        this.syncStack = syncStack;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费： " + i);
            syncStack.pop();
        }
    }
}
