package com.pd.zookeeper;

import com.pd.zookeeper.curator.annotation.ZooLock;
import com.pd.zookeeper.curator.aspectj.DistributedLockAspect;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-12-2 14:01
 */
@SpringBootTest
@Slf4j
public class CuratorDistributedLockTest {
    @Autowired
    private CuratorFramework client;
    private Integer count = 10000;
    private ExecutorService executorService = Executors.newFixedThreadPool(1000);
    public Integer getCount() {
        return count;
    }

    @Test
    void mutexLockTest() throws InterruptedException{
        IntStream.range(0, 10000).forEach(i -> executorService.execute(this::manualBuy));
        TimeUnit.MINUTES.sleep(1);
    }
    private void manualBuy() {
        String lockPath = "/buy";
        log.info("try to buy sth.");
        try {
            InterProcessMutex lock = new InterProcessMutex(client, lockPath);
            try {
                if (lock.acquire(1, TimeUnit.MINUTES)) {
                    count--;
                    log.info("count值为{}", count);
                    log.info("buy successfully!");
                }
            } finally {
                lock.release();
            }
        } catch (Exception e) {
            log.error("zk error");
        }
    }

    @Test
    void aopMutexLockTest()throws InterruptedException{
        // 测试类中使用AOP需要手动代理
        CuratorDistributedLockTest target = new CuratorDistributedLockTest();
        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        DistributedLockAspect aspect = new DistributedLockAspect(client);
        factory.addAspect(aspect);
        CuratorDistributedLockTest proxy = factory.getProxy();

        IntStream.range(0,10000).forEach(i->executorService.execute(()->proxy.aopBuy(i)));
        TimeUnit.MINUTES.sleep(1);
        log.error("count值为{}", proxy.getCount());
    }

    @ZooLock(key = "buy", timeout = 1, timeUnit = TimeUnit.MINUTES)
    private void aopBuy(int userId) {
        log.info("{} 正在出库。。。", userId);
        count--;
        log.info("count值为{}", count);
        log.info("{} 扣库存成功。。。", userId);
    }
}
