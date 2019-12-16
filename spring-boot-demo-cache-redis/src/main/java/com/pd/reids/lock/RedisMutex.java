package com.pd.reids.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

import java.util.UUID;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/12/11 14:01
 */
@Service
@Slf4j
public class RedisMutex {
    private static final String LOCK_KEY = "dis_lock";
    private Jedis jedis = null;
    private final Object LOCAL_LOCK = new Object();


    public static void main(String[] args) {
        RedisMutex de = new RedisMutex();
        String lockId = de.lock();
        de.unlock(lockId);
    }
    public String lock(){
        return lock(5000);
    }

    /**
     *
     * @param acquireTimeOut  获取分布式锁超时时间，
     */
    public String lock(long acquireTimeOut) throws JedisException {
        try {
            long end = System.currentTimeMillis() + acquireTimeOut;
            jedis = new Jedis("10.0.12.74",6379);
            while(System.currentTimeMillis()<end){
                String lockValue = UUID.randomUUID().toString();
                synchronized (LOCAL_LOCK) {
                    if (1 == jedis.setnx(LOCK_KEY, lockValue)) {
                        return lockValue;
                    }
                }
                //暂停一下再竞争锁
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }catch (JedisException je){
            log.info(je.getMessage());
        }finally {
            jedis.close();
        }
        return null;
    }


    public synchronized void unlock(String lockId){
        try {
            Jedis jedis = new Jedis("10.0.12.74",6379);
            synchronized (LOCAL_LOCK) {
                if (jedis.get(LOCK_KEY).equals(lockId)) {
                    jedis.del(LOCK_KEY);
                }else {
                    throw new IllegalArgumentException("分布式锁ID错误");
                }
            }
        }catch (JedisException je){
            log.info(je.getMessage());
        }finally {
            jedis.close();
        }
    }
}
