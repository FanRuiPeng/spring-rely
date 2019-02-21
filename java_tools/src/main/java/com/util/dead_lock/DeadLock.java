package com.util.dead_lock;

import java.util.LinkedHashMap;

public class DeadLock implements Runnable {
    int a, b;

    public DeadLock(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (Integer.valueOf(a)) {
            synchronized (Integer.valueOf(b)) {
                System.out.println(a + b);
            }
        }
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            new Thread(new DeadLock(1, 2)).start();
//            new Thread(new DeadLock(2, 1)).start();
//        }
        Object o = new Object();
        LinkedHashMap<String, Object> hashSet = new LinkedHashMap<>();
//        hashSet.se
        hashSet.put("46465", o);
        hashSet.put("3r3r", o);
        hashSet.put("sfs",o);
        hashSet.put("67utu",o);
        hashSet.put("u76jyng",o);
        hashSet.forEach((k, v) -> System.out.println(k));
    }
}
