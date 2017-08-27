package com.bmf.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.TimeUnit;

/**
 * Created by BMF on 2017/6/4.
 */
@Component
public class RedisTool {

    private static Logger logger = LoggerFactory.getLogger(RedisTool.class.getName());

    @Autowired
    private RedisTemplate redisTemplate;

    
    public Object put(String key, String value, Long time) {new JedisConnectionFactory().setPoolConfig(new JedisPoolConfig());
        return redisTemplate.execute(new PutInRedis(key, value, time));
    }

    public Object get(String a) {
        Object execute = redisTemplate.execute(new GetFromRedis(a));
        RedisModel r = new RedisModel();
        r.setKey(a);
        r.setValue(execute);
        return r;
    }

private class RedisModel {
        private String key;
        private Object value;

    public RedisModel() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RedisModel that = (RedisModel) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RedisModel{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}

    private class PutInRedis implements SessionCallback {

        private String key;
        private Object value;
        private Long time;

        public PutInRedis(String key, Object value, Long time) {
            this.key = key;
            this.value = value;
            this.time = time;
        }

        @Override
        public Object execute(RedisOperations operations) throws DataAccessException {
            operations.multi();
            try {
                BoundHashOperations boundHashOperations = operations.boundHashOps(key);
                boundHashOperations.put(key, value);
                boundHashOperations.expire(time, TimeUnit.SECONDS);
                logger.info("operation exec put 成功");
                operations.exec();
            } catch (Exception e) {
                e.printStackTrace();
                operations.discard();
                logger.error("operation discard, please check it put 失败");
                return "0";
            }
            return "1";
        }
    }

    private class GetFromRedis implements SessionCallback {
        private String key;

        public GetFromRedis(String key) {
            this.key = key;
        }

        @Override
        public Object execute(RedisOperations operations) throws DataAccessException {
//            operations.multi();
            Object o;
            try {
                BoundHashOperations boundHashOperations = operations.boundHashOps(key);
                o = boundHashOperations.get(key);
                logger.info("operation exec get 成功 : " + o);
//                operations.exec();
            } catch (Exception e) {
                e.printStackTrace();
//                operations.discard();
                logger.error("operation discard, please check it get 失败");
                return "0";
            }
            return o;
        }
    }

}
