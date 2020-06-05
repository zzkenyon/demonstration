package com.pd.redis.dislock;

import org.springframework.stereotype.Service;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-20 16:50
 */
@Service
public class LockTestService {
    @RedisLock(value = "test",timeOut = 1000)
    public void print(){
        System.out.println("******************获取锁成功*******************");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
