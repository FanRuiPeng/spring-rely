package com.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest implements Runnable {

    private List list = new ArrayList();

    private Lock lock = new ReentrantLock(false);

    public static void main(String args[]) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        new Thread(reentrantLockTest).start();
        new Thread(reentrantLockTest).start();
    }

    public void run() {
        if (lock.tryLock()) {
//        lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " acquired lock !");
                for (int i = 0; i < 5; i++) {
                    list.add(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                list.forEach(System.out::println);
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " released lock !");
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " acquire lock failed !");
        }
    }
}
