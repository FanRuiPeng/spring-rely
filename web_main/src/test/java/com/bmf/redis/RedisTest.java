package com.bmf.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@ContextConfiguration("/config/redis.xml")
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;

    @Test
    public void set() throws Exception {
        Long a = listOps.leftPush("a", "1");
        System.out.println(a);

    }

    @Test
    public void get() throws Exception {
        String a = listOps.leftPop("a");
        System.out.println(a);
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void strSet() throws Exception {
        Long b = stringRedisTemplate.opsForList().leftPush("b", "2");
        System.out.println(b);
    }

    @Test
    public void callback() throws Exception {
        Object execute = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                String clientName = connection.getClientName();
                System.out.println(clientName);
                Long aLong = connection.dbSize();
                System.out.println(aLong);
                ((StringRedisConnection) connection).set("k", "v");
                return clientName;
            }
        });
        System.out.println(execute);
    }
}
