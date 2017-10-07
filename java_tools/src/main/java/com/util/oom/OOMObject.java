package com.util.oom;

import java.util.ArrayList;

public class OOMObject {

    static class MemoryObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        ArrayList<MemoryObject> memoryObjects = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            memoryObjects.add(new MemoryObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
//        fillHeap(1000);
    }
}
