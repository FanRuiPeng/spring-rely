package com.bmf.tools;

import java.util.concurrent.*;

/**
 * Created by BMF on 2017/7/22.
 */
public class TaskTest {

    public static void main(String[] args) {
        String a = "1";
        String b = "2";
        String c = "12";
        String d = a + b;
        String e = "1" + "2";
        System.out.println("12" == "1" + "2");
        System.out.println(d == c);
        System.out.println(c == e);
        System.out.println(d.equals(c));
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        Future<String> submit = executorService.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                Thread.sleep(1000);
//                System.out.println(Thread.currentThread().getName());
//                return Thread.currentThread().getName();
//            }
//        });
//
//        String s = null;
//        try {
//            s = submit.get(11, TimeUnit.MINUTES);
//            System.out.println(s);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
    }
}
