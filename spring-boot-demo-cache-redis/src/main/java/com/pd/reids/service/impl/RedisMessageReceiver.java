package com.pd.reids.service.impl;

import com.pd.reids.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/11/29 14:19
 */
@Service
public class RedisMessageReceiver {
    @Autowired
    private RedisService service;

    public void receiveMessage(String message) {
        System.out.println("消息来了："+ message);
        String config = service.get("global_config");
        System.out.println("新配置为：" + config);
    }
}
