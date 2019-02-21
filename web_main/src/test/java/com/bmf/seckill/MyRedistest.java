package com.bmf.seckill;

import redis.clients.jedis.Jedis;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class MyRedistest {

    public static void main(String[] args) {
        AtomicReference<Saft> reference = new AtomicReference<>();
        ExecutorService executor = Executors.newFixedThreadPool(200);
        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    boolean b = reference.compareAndSet(null, new Saft(1, "2"));
                    System.out.println(b);
                }
            });
        }
//        final String watchkeys = "watchkeys";
////        ExecutorService executor = Executors.newFixedThreadPool(200);  //20个线程池并发数
//        final Jedis jedis = new Jedis("112.74.19.176", 6300);
//        jedis.auth("codespace0123HWiemri230xo43ie_________________");
//        jedis.select(2);
//        jedis.set(watchkeys, "100");//设置起始的抢购数
//        // jedis.del("setsucc", "setfail");
//        jedis.close();
//        for (int i = 0; i < 1000; i++) {//设置1000个人来发起抢购
////            executor.execute(new MyRunnable("user" + i));
//            new Thread(new MyRunnable("user" + i)).start();
//        }
//        executor.shutdown();
    }

    static class Saft {
        int a;
        String b;

        public Saft(int a, String b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Saft saft = (Saft) o;
            return a == saft.a &&
                    Objects.equals(b, saft.b);
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

        @Override
        public String toString() {
            return "Saft{" +
                    "a=" + a +
                    ", b='" + b + '\'' +
                    '}';
        }
    }
}

class MyRunnable implements Runnable {
    String watchkeys = "watchkeys";// 监视keys
    Jedis jedis;
    String userinfo;

    public MyRunnable() {
    }

    public MyRunnable(String uinfo) {
        jedis = new Jedis("112.74.19.176", 6300);
        jedis.auth("codespace0123HWiemri230xo43ie_________________");
        jedis.select(2);
        this.userinfo = uinfo;
    }

    @Override
    public void run() {
        try {
//            jedis.watch(watchkeys);// watchkeys
            String val = jedis.get(watchkeys);
            int valint = Integer.valueOf(val);
            if (valint <= 100 && valint >= 1) {
//                Transaction tx = jedis.multi();// 开启事务
                // tx.incr("watchkeys");
//                tx.incrBy("watchkeys", -1);
//                List<Object> list = tx.exec();// 提交事务，如果此时watchkeys被改动了，则返回null
                Long aLong = jedis.incrBy(watchkeys, -1);
//                if (list == null || list.size() == 0) {
                if (aLong < 0) {
                    String failuserifo = "fail" + userinfo;
                    String failinfo = "用户：" + failuserifo + "商品争抢失败，抢购失败";
                    System.out.println(failinfo);
                    /* 抢购失败业务逻辑 */
//                    jedis.setnx(failuserifo, failinfo);
                } else {
//                    for (Object succ : list) {

                    String succuserifo = "succ" + aLong.toString() + userinfo;
                    String succinfo = "用户：" + succuserifo + "抢购成功，当前抢购成功人数:" + (1 - (valint - 100));
                    System.out.println(succinfo);
                    /* 抢购成功业务逻辑 */
//                        jedis.setnx(succuserifo, succinfo);
                }
//                }
            } else {
                String failuserifo = "kcfail" + userinfo;
                String failinfo1 = "用户：" + failuserifo + "商品被抢购完毕，抢购失败";
                System.out.println(failinfo1);
//                jedis.setnx(failuserifo, failinfo1);
                // Thread.sleep(500);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }
}