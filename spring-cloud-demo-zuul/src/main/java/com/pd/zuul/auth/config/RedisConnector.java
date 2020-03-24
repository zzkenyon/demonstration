package com.pd.zuul.auth.config;/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 14:31
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 14:31
 */
@Component
public class RedisConnector {
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        // 重定义redis序列化器，去除key值首部乱码
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    public void put(String prefix, String key, String value, long expire) {
        redisTemplate.opsForValue().set(prefix+":"+key, value, expire, TimeUnit.SECONDS);
    }

    public Object get(String prefix, String key) {
        return redisTemplate.opsForValue().get(prefix+":"+key);
    }

    public long inc(String prefix, String key) {
        return this.inc(prefix, key,1);
    }

    public long inc(String prefix, String key, long delta) {
        return redisTemplate.opsForValue().increment(prefix+":"+key,delta);
    }

    public void delete(String prefix, String key) {
        redisTemplate.delete(prefix+":"+key);
    }
}
