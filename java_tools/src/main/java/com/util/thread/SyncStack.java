package com.util.thread;

/**
 * Created by BMF on 2018/3/12.
 */
public class SyncStack {
    int index = 0;
    Product[] products = new Product[10];

    public synchronized void push(Product p) {
        while (index == products.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("--------push----------------");
        this.notify();
        products[index] = p;
        index++;
    }

    public synchronized Product pop() {
        while (index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("--------pop----------------");
        this.notify();
        index--;
        return products[index];

    }
}
