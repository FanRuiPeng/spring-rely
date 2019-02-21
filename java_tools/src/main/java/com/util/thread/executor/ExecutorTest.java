package com.util.thread.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {

    public static void main(String[] args) {
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(200);
        ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(200);
        for (int i = 0; i < 20; i++) {
            ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new RunDemo("task_" + i), 1, TimeUnit.MINUTES);
        }

        BlockingQueue<Runnable> queue = scheduledExecutorService.getQueue();
//        boolean contains = queue.contains((ScheduledFuture)new RunDemo("task_" + 1));
//        System.out.println(contains);
        for (Runnable r : queue) {
//            String threadName = ((RunDemo) r).getThreadName();
            ScheduledFuture r1 = (ScheduledFuture) r;
//            r1.get()
//            System.out.println(threadName);
        }
    }

    static class RunDemo implements Runnable {
        private String threadName;

        public RunDemo(String threadName) {
            this.threadName = threadName;
        }

        public String getThreadName() {
            return threadName;
        }

        public RunDemo setThreadName(String threadName) {
            this.threadName = threadName;
            return this;
        }

        @Override
        public void run() {

        }
    }
}
