package com.pd.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-11-29 10:01
 */
@SpringBootTest
class CuratorOperatorTest {
    @Autowired
    private CuratorFramework client;
    @Test
    void createNode() throws Exception{
        client.create()
                .creatingParentContainersIfNeeded() //递归创建，没有父节点则创建父节点
                .withMode(CreateMode.PERSISTENT)//节点类型设置
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)//访问权限设置，使用官方API
                .forPath("/curator/child_01","abcdefg".getBytes());
    }

    @Test
    void createContainerNode() throws Exception{//创建容器节点
        client.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.CONTAINER)//当最后一个子节点删除的时候，服务器会在未来的一段时间删除该容器节点(不是立刻删除)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath("/curator/container","12345".getBytes());
    }

    @Test
    void createTimingNode()throws Exception{//定时节点
        client.create()
                .withTtl(10000)//有效期，单位ms
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.PERSISTENT_SEQUENTIAL_WITH_TTL)//在一定的时间内,如果没有操作该节点,并且该节点没有子节点,那么服务器将自动删除该节点
                .withACL(Collections.singletonList(new ACL(ZooDefs.Perms.ALL, new Id("digest", "zzk:G2RdrM8e0u0f1vNCj/TI99ebRMw="))))
                .forPath("/curator/timing","timing".getBytes());
    }

    @Test
    void get()throws Exception{
        Stat stat = new Stat();
        byte[] data = client.getData()
                .storingStatIn(stat)
                .forPath("/curator/child_01");
        System.out.println("=====>该节点信息为：" + new String(data));
        System.out.println("=====>该节点的数据版本号为：" + stat.getVersion());
    }

    @Test
    void watch()throws Exception{
        byte[] bytes = client.getData()
                .usingWatcher((Watcher) watchedEvent -> {
                    System.out.println("=====>wathcer触发了。。。。");
                    System.out.println(watchedEvent);
                })
                .forPath("/curator/child_01");
        System.out.println("=====>获取到的节点数据为："+new String(bytes));
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    void set()throws Exception{
        Stat stat = client.setData()
                .withVersion(-1)
                .forPath("/merryyou","i love you".getBytes());
        System.out.println("=====>修改之后的版本为：" + stat.getVersion());
    }

    @Test
    void CheckExists()throws Exception{
        Stat existsNodeStat = client.checkExists().forPath("/curator/timing");
        if(existsNodeStat == null){
            System.out.println("=====>节点不存在");
        }
        if(existsNodeStat.getEphemeralOwner() > 0){
            System.out.println("=====>临时节点");
        }else{
            System.out.println("=====>持久节点");
        }
    }


}
