package com.pd.reids.service.impl;

import com.pd.reids.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


/**
 * @author zhaozhengkang
 * @description
 * @date 2019/11/29 13:59
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String, String> template;


    @Override
    public String get(String key) {
        return template.opsForValue().get(key);
    }
    @Override
    public void set(String key,String val){
        template.opsForValue().set(key,val);
    }

}
