package com.util.thread;

/**
 * Created by BMF on 2018/3/12.
 */
public class TestProduct {

    public static void main(String[] args) {
        SyncStack syncStack = new SyncStack();
        Producer producer = new Producer(syncStack);
        Consumer consumer = new Consumer(syncStack);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
