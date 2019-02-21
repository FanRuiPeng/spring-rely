package com.bmf.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    /**
     * -------------------------StringRedisTemplate------------------------------------------
     */

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void strSet() throws Exception {
//        Long b = stringRedisTemplate.opsForList().leftPush("b", "2");
//        System.out.println(b);

        List<Object> objects = stringRedisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
                for (int i = 0; i < 5; i++) {
                    stringRedisConn.rPop("myqueue");
                }
                return null;
            }
        });

//        // executed on thread bound connection
//        redisTemplate.opsForValue().set("foo", "bar");
//
//// read operation executed on a free (not tx-aware)
//        Set<String> keys = redisTemplate.keys("*");
//        keys.forEach(System.out::println);
//
//// returns null as values set within transaction are not visible
//        String foo = redisTemplate.opsForValue().get("foo");
//        System.out.println(foo);

    }

    @Test
    public void callback() throws Exception {
        Object execute = redisTemplate.execute((RedisCallback<String>) connection -> {
            String clientName = connection.getClientName();
            System.out.println(clientName);
            Long aLong = connection.dbSize();
            System.out.println(aLong);
            ((StringRedisConnection) connection).set("k", "v");
            return clientName;
        });
        System.out.println(execute);
    }

    /**
     * ----------------------------hashMapper----------------------------------------
     */
    @Resource(name = "redisTemplate")
    private HashOperations<String, byte[], byte[]> hashOperations;

    // 实体类的set方法返回值为void 即标志的set方法，builder式的不行
    HashMapper<Person, byte[], byte[]> mapper = new BeanUtilsHashMapper(Person.class);

    @Test
    public void writeHash() {
        Person man = new Person();
        man.setFirstName("张");
        man.setLastName("三");
//        Address address = new Address();
//        address.setCity("1");
//        address.setCountry("1");
//        man.setAddress(address);
        Map<byte[], byte[]> map = mapper.toHash(man);
        hashOperations.putAll("man", map);

        Map<byte[], byte[]> manMap = hashOperations.entries("man");
        Person man1 = mapper.fromHash(manMap);
        System.out.println(man1.getFirstName() + " : " + man1.getLastName());
    }
}
