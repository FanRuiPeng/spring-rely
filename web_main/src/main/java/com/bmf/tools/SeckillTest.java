package com.bmf.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class SeckillTest {

    public static final String watchkeys = "watchkeys";
    public static final String watchkeysList = "watchkeysList";

    @Autowired
    private RedisTemplate redisTemplate;

    public void testRedis() {
        ExecutorService executor = Executors.newFixedThreadPool(3);  //20个线程池并发数
        BoundValueOperations<String, Integer> valueOps = redisTemplate.boundValueOps(watchkeys);
        valueOps.set(100, 1, TimeUnit.HOURS);//设置起始的抢购数
        BoundListOperations listOps = redisTemplate.boundListOps(watchkeysList);
        for (int i = 0; i < 100; i++) {
            listOps.leftPush(1);
        }

        for (int i = 0; i < 200; i++) {//设置1000个人来发起抢购
            executor.execute(new MyRunnable(redisTemplate, "user" + i));
        }
        executor.shutdown();
    }

    static class MyRunnable implements Runnable {
        String userinfo;
        RedisTemplate redisTemplate;

        public MyRunnable() {

        }

        public MyRunnable(RedisTemplate redisTemplate, String uinfo) {
            this.userinfo = uinfo;
            this.redisTemplate = redisTemplate;
        }

        @Override
        public void run() {
//            redisTemplate.execute(new IncrementCallBack(userinfo));
            redisTemplate.execute(new ListCallBack(userinfo));
        }
    }

    /**
     * 用increment实现
     */
    static class IncrementCallBack implements SessionCallback {
        String userinfo;

        public IncrementCallBack(String userinfo) {
            this.userinfo = userinfo;
        }

        @Override
        public Object execute(RedisOperations operations) throws DataAccessException {
            try {
                operations.watch(SeckillTest.watchkeys);
                BoundValueOperations<String, Integer> valueOps = operations.boundValueOps(SeckillTest.watchkeys);
                int valint = valueOps.get();
                System.out.println(userinfo + " 进来了 get value is : " + valint);
                if (valint <= 100 && valint >= 1) {
                    List list = null;
                    long start = System.currentTimeMillis();
                    do {
                        long now = System.currentTimeMillis();
                        if (now - start >= 1000) {
                            String failuserifo = "fail" + userinfo;
                            System.out.println("用户：" + failuserifo + "商品争抢失败，抢购失败");
                            break;
                        }
                        operations.multi();// 开启事务
                        Long increment = valueOps.increment(-1);
                        list = operations.exec();// 提交事务，如果此时watchkeys被改动了，则返回null
                        System.out.println("用户：cycle " + userinfo + "商品争抢锁");
                    } while (list == null || list.size() == 0);

                    for (Object succ : list) {
                        String succuserifo = "succ" + succ.toString() + userinfo;
                        String succinfo = "用户：" + succuserifo + "抢购成功，当前抢购成功人数:" + (1 - (valint - 100));
                        System.out.println(succinfo);
                        operations.boundValueOps(userinfo).setIfAbsent(succinfo);
                    }
                } else {
                    String failuserifo = "kcfail" + userinfo;
                    String failinfo1 = "用户：" + failuserifo + "商品被抢购完毕，抢购失败";
                    System.out.println(failinfo1);
                }
            } catch (
                    Exception e) {
                e.printStackTrace();
            }
            return 1;
        }
    }

    /**
     * list实现
     */
    static class ListCallBack implements SessionCallback {
        String userinfo;

        public ListCallBack(String userinfo) {
            this.userinfo = userinfo;
        }

//        @Override
//        public Object execute(RedisOperations operations) throws DataAccessException {
//            try {
//                BoundListOperations<String, Integer> listOps = operations.boundListOps(SeckillTest.watchkeysList);
//                Long size = listOps.size();
//                System.out.println(userinfo + " 进来了 stock size is : " + size);
//                if (size >= 1) {
//                    Integer integer = listOps.leftPop();
//                    if (null != integer && integer > 0) {
//                        String succinfo = "用户：" + userinfo + "抢购成功，当前抢购成功人数:" + (100 - listOps.size());
//                        System.out.println(succinfo);
//                        operations.boundValueOps(userinfo).setIfAbsent(succinfo);
//                    } else {
//                        String failuserifo = "fail" + userinfo;
//                        System.out.println("用户：" + failuserifo + "商品争抢失败，抢购失败");
//                    }
//                } else {
//                    String failuserifo = "kcfail" + userinfo;
//                    String failinfo1 = "用户：" + failuserifo + "商品被抢购完毕，抢购失败";
//                    System.out.println(failinfo1);
//                }
//            } catch (
//                    Exception e) {
//                e.printStackTrace();
//            }
//            return 1;
//        }

        @Override
        public Object execute(RedisOperations operations) throws DataAccessException {
            try {
                operations.watch(SeckillTest.watchkeysList);
                BoundListOperations<String, Integer> listOps = operations.boundListOps(SeckillTest.watchkeysList);
                Long size = listOps.size();
                System.out.println(userinfo + " 进来了 stock size is : " + size);
                if (size > 0) {
                    List list = null;
                    long start = System.currentTimeMillis();
                    do {
                        long now = System.currentTimeMillis();
                        if (now - start >= 1000) {
                            String failuserifo = "fail" + userinfo;
                            System.out.println("用户：" + failuserifo + "商品争抢失败，抢购失败");
                            break;
                        }
                        operations.multi();// 开启事务
                        Integer pop = listOps.leftPop();
                        list = operations.exec();// 提交事务，如果此时watchkeys被改动了，则返回null
                        System.out.println("用户：cycle " + userinfo + "商品争抢锁");
                    } while (list == null || list.size() == 0);

                    for (Object succ : list) {
                        String succuserifo = "succ" + succ.toString() + userinfo;
                        String succinfo = "用户：" + succuserifo + "抢购成功，当前抢购成功人数:" + (100 - listOps.size());
                        System.out.println(succinfo);
                        operations.boundValueOps(userinfo).setIfAbsent(succinfo);
                    }
                } else {
                    String failuserifo = "kcfail" + userinfo;
                    String failinfo1 = "用户：" + failuserifo + "商品被抢购完毕，抢购失败";
                    System.out.println(failinfo1);
                }
            } catch (
                    Exception e) {
                e.printStackTrace();
            }
            return 1;
        }
    }
}
