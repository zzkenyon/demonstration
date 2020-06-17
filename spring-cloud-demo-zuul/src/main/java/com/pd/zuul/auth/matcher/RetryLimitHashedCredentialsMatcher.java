package com.pd.zuul.auth.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/15 10:11
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    private RedisTemplate<String,Object> redisService;
    @Autowired
    public void setRedisService(RedisTemplate<String, Object> redisService) {
        this.redisService = redisService;
    }

    /**
     * 缓存无需删除，设置1小时失效。
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        if (super.doCredentialsMatch(token, info)) {
            return true;
        } else {
            String username = (String) token.getPrincipal();
            AtomicInteger retryCount = (AtomicInteger) redisService.opsForValue().get(username);
            if (retryCount == null) {
                redisService.opsForValue().set(username, new AtomicInteger(0));
            }
            assert retryCount != null;
            if (retryCount.incrementAndGet() > 5) {
                throw new ExcessiveAttemptsException();
            } else {
                redisService.opsForValue().set(username,retryCount);
            }
            return false;
        }
    }
}
