package com.pd.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-20 15:12
 */
@Configuration
public class JedisClient {
    @Bean
    public JedisPool jedisPool(){
        return new JedisPool("10.0.12.74", 6379);
    }

}
