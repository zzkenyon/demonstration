package com.pd.redis.dislock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/5 10:01
 */
@Configuration
public class JedisPoolConfig {
    @Bean
    public JedisPool jedisPool (){
        return new JedisPool("10.0.12.74",6379);
    }
}
