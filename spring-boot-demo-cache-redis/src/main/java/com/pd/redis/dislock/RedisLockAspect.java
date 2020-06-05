package com.pd.redis.dislock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-20 14:28
 */
@Aspect
@Component
public class RedisLockAspect {
    private static final String LOCK_KEY = "redis_lock_";


    private RedisMutex redisMutex;
    @Autowired
    public void setRedisMutex(RedisMutex redisMutex) {
        this.redisMutex = redisMutex;
    }

    @Pointcut("@annotation(com.pd.redis.dislock.RedisLock)")
    public void doLock(){

    }

    @Around("doLock()")
    public Object around(ProceedingJoinPoint Point)throws Throwable{
        MethodSignature methodSignature = (MethodSignature)Point.getSignature();
        Method method = methodSignature.getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        long timeOut = redisLock.timeOut();
        String value = redisLock.value();
        String redisLockKey;
        if("".equals(value)){
            redisLockKey= LOCK_KEY + "default";
        }else{
            redisLockKey = LOCK_KEY + value;
        }
        try {
            if(redisMutex.lock(redisLockKey,timeOut)){
                return Point.proceed();
            }else {
                throw new RuntimeException("获取redis锁失败");
            }
        }finally {
            redisMutex.unlock(redisLockKey);
        }
    }
}
