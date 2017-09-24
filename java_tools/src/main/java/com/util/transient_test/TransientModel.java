package com.util.transient_test;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.*;

public class TransientModel implements Serializable {
    transient int a;
    int b;

    public TransientModel(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "TransientModel{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    //    public static void main(String[] args) {
//        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("D://a.text"))) {
//            outputStream.writeObject(new TransientModel(1, 2));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (new File("D://a.text").exists()) {
//            System.out.println("写入文件成功");
//            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D://a.text"))) {
//                Object o = objectInputStream.readObject();
//                System.out.println(o.toString());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public static void main(String[] args) {
//        SerialExecutor serialExecutor = new SerialExecutor(new ScheduledThreadPoolExecutor(2));
//        for (int i = 0; i < 10; i++) {
//            serialExecutor.tasks.add(() -> {
//                System.out.println(Thread.currentThread().getName());
//            });
//        }
//        serialExecutor.execute(() -> {
//            System.out.println(Thread.currentThread().getName());
//        });
        Executors.newFixedThreadPool(1);
//        new ScheduledThreadPoolExecutor(1, new ThreadPoolExecutor.AbortPolicy()).scheduleAtFixedRate();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit1 = executorService.submit(() -> {
            return "0";
        });
        Future<String> submit = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(10000);
                return "1";
            }
        });
        try {
            System.out.println(submit1.get());
            if (submit.isDone()) {
                System.out.println(submit.get());
            } else {
                executorService.shutdown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        Callable<String> callable = Executors.callable(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("start");
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("stop");
//            }
//        }, "1");
//        try {
//            String call = callable.call();
//            System.out.println(call);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}

class SerialExecutor implements Executor {
    final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
    final Executor executor;
    Runnable active;

    SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    public synchronized void execute(final Runnable r) {
        tasks.offer(new Runnable() {
            public void run() {
                try {
                    r.run();
                } finally {
                    scheduleNext();
                }
            }
        });
        if (active == null) {
            scheduleNext();
        }
    }


    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }
}
