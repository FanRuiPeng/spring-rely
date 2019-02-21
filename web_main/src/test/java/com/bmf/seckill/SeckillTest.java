package com.bmf.seckill;

import com.bmf.func.TestBeanInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@ContextConfiguration("../conf/spring-test.xml")
//@DirtiesContext
@RunWith(SpringRunner.class)
//@TestPropertySource(properties = {"a = 1", "b = 2"})
@TestBeanInterface
public class SeckillTest {

    public static final String watchkeys = "watchkeys";

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis() {
        String watchkeysList = "watchkeysList";
        BoundListOperations listOps = redisTemplate.boundListOps(watchkeysList);
        List<Integer> collect = Stream.generate(() -> 1)
                .limit(100)
                .collect(Collectors.toList());
        listOps.leftPushAll(collect.toArray());
//        redisTemplate.execute(new SessionCallback() {
//            @Override
//            public Object execute(RedisOperations operations) throws DataAccessException {
//                operations.multi();
//                for (int i = 0; i < 100; i++) {
//                    listOps.leftPush(1);
//                }
//                operations.exec();
//                return null;
//            }
//        });
//        ExecutorService executor = Executors.newFixedThreadPool(20);  //20个线程池并发数
//        BoundValueOperations<String, String> valueOps = redisTemplate.boundValueOps(watchkeys);
//        valueOps.set("100", 1, TimeUnit.HOURS);//设置起始的抢购数
//
//        for (int i = 0; i < 1000; i++) {//设置1000个人来发起抢购
//            executor.execute(new MyRunnable(redisTemplate, "user" + i));
//        }
//        executor.shutdown();
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
            redisTemplate.execute(new CallBack(userinfo));
        }
    }

    static class CallBack implements SessionCallback {
        String userinfo;

        public CallBack(String userinfo) {
            this.userinfo = userinfo;
        }

        @Override
        public Object execute(RedisOperations operations) throws DataAccessException {
            try {
                System.out.println(111);
                operations.watch(SeckillTest.watchkeys);
                BoundValueOperations<String, String> valueOps = operations.boundValueOps(SeckillTest.watchkeys);
                String val = valueOps.get();
                int valint = Integer.valueOf(val);
                System.out.println(userinfo + " 进来了 get value is : " + val);
                if (valint <= 100 && valint >= 1) {
                    operations.multi();// 开启事务
                    Long increment = valueOps.increment(-1);
                    List list = operations.exec();// 提交事务，如果此时watchkeys被改动了，则返回null
                    if (list == null || list.size() == 0) {
                        String failuserifo = "fail" + userinfo;
                        String failinfo = "用户：" + failuserifo + "商品争抢失败，抢购失败";
                        System.out.println(failinfo);
                    } else {
                        for (Object succ : list) {
                            String succuserifo = "succ" + succ.toString() + userinfo;
                            String succinfo = "用户：" + succuserifo + "抢购成功，当前抢购成功人数:" + (1 - (valint - 100));
                            System.out.println(succinfo);
                        }
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
