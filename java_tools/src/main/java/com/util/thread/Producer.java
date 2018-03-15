package com.util.thread;

/**
 * Created by BMF on 2018/3/12.
 */
public class Producer implements Runnable {
    SyncStack syncStack;

    public Producer(SyncStack syncStack) {
        this.syncStack = syncStack;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产：" + i);
            syncStack.push(new Product(i));
        }
    }
}
