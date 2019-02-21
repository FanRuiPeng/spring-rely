package com.util.concurrent;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest implements Runnable {

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String args[]) {
        ReentrantReadWriteLockTest reentrantLockTest = new ReentrantReadWriteLockTest();
        new Thread(reentrantLockTest).start();
        new Thread(reentrantLockTest).start();
    }

    public void run() {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired lock !");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("C:\\Users\\BMF\\Desktop\\java current.txt"));
            byte[] bytes = new byte[24];
            int read = bufferedInputStream.read(bytes);
            while (read != -1) {
                String s = new String(bytes);
                System.out.println(s);
                read = bufferedInputStream.read(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
            System.out.println(Thread.currentThread().getName() + " released lock !");
        }
    }
}
