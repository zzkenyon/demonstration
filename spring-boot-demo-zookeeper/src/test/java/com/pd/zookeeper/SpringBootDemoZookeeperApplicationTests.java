package com.pd.zookeeper;

import com.pd.zookeeper.zk.BaseZookeeper;
import com.pd.zookeeper.zk.ZkProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootDemoZookeeperApplicationTests {
    @Autowired
    private ZkProperties properties;
    @Autowired
    private BaseZookeeper zookeeper;

    /**
     * zookeeper官方jar包使用测试
     * @throws Exception
     */
    @Test
    void zkTest() throws Exception{
        String path = "/merryyou";
        zookeeper.connectZookeeper(properties.getHost(),properties.getSessionTimeOut());
        System.out.println(zookeeper.watchData(path));
        //主线程休眠
        Thread.sleep(Integer.MAX_VALUE);
    }
}
