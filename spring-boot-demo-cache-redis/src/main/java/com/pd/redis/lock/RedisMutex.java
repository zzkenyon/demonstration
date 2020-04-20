package com.pd.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
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
    private final Object LOCAL_LOCK = new Object();
    private JedisPool pool;
    @Autowired
    public void setPool(JedisPool pool) {
        this.pool = pool;
    }

    public static void main(String[] args) {
        RedisMutex de = new RedisMutex();
    }

    /**
     *
     * @param acquireTimeOut  获取分布式锁超时时间，
     */
    public boolean lock(String key,long acquireTimeOut) throws JedisException {
        log.info("开始获取锁");
        Jedis jedis = pool.getResource();
        try {
            long end = System.currentTimeMillis() + acquireTimeOut;
            while(System.currentTimeMillis()<end){
                String lockValue = UUID.randomUUID().toString();
                synchronized (LOCAL_LOCK) {
                    if(1==jedis.setnx(key,lockValue)){
                        return true;
                    }else{
                        //暂停一下再竞争锁
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }catch (JedisException je){
            log.info(je.getMessage());
        }finally {
            jedis.close();
        }
        return false;
    }


    public void unlock(String lockKey){
        Jedis jedis = pool.getResource();
        try {
            synchronized (LOCAL_LOCK){
                jedis.del(lockKey);
            }
        }catch (JedisException je){
            log.info(je.getMessage());
        }finally {
            jedis.close();
        }
    }
}
