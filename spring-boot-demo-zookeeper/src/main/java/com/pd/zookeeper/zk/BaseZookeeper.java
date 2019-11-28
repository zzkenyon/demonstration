package com.pd.zookeeper.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhaozhengkang
 * @description 官方jar包接口封装类
 * @version
 * @date 2019-11-28 14:44:23
 */
@Slf4j
@Service
public class BaseZookeeper implements Watcher {
    private ZooKeeper zookeeper;
    /**
     * 超时时间
     */
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private static Stat stat = new Stat();

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            if(event.getType() == Event.EventType.None && null == event.getPath()) {
                log.info("Watch received event");
                countDownLatch.countDown();
            }else if(event.getType() == Event.EventType.NodeDataChanged){
                try{
                    log.info("配置已修改，新值为：" + new String(zookeeper.getData(event.getPath(), true, stat)));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 连接zookeeper
     * @param host
     * @throws Exception 
     */
     public void connectZookeeper(String host,int sessionTimeOut) throws Exception {
        zookeeper = new ZooKeeper(host, sessionTimeOut, this);
        countDownLatch.await();
        log.info("zookeeper connection success");
    }
    /**
     * 创建节点
     * @param path
     * @param data
     * @throws Exception
     */
    public String createNode(String path, String data) throws Exception {
        return this.zookeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 获取路径下所有子节点
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException  
     */
    public List<String> getChildren(String path) throws KeeperException, InterruptedException {
        List<String> children = zookeeper.getChildren(path, false);
        return children;
    }

    /**
     * 获取节点上面的数据
     * @param path  路径
     * @return
     * @throws KeeperException
     * @throws InterruptedException  
     */
    public String getData(String path) throws KeeperException, InterruptedException {
        byte[] data = zookeeper.getData(path, false, null);
        if (data == null) {
            return "";
        }
        return new String(data);
    }
    /**
     * 获取并监视节点上面的数据
     * @param path  路径
     * @return
     * @throws KeeperException
     * @throws InterruptedException  
     */
    public String watchData(String path) throws KeeperException,InterruptedException{
        byte[] data = zookeeper.getData(path,true,null);
        if(data == null){
            return "";
        }
        return new String(data);
    }

    /**
     * 设置节点信息
     * @param path  路径
     * @param data  数据
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public Stat setData(String path, String data) throws KeeperException, InterruptedException{
        Stat stat = zookeeper.setData(path, data.getBytes(), -1);
        return stat;
    }

    /**
     * 删除节点
     * @param path
     * @throws InterruptedException
     * @throws KeeperException
     */
    public void deleteNode(String path) throws InterruptedException, KeeperException{
        zookeeper.delete(path, -1);
    }

    /**
     * 获取创建时间
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String getCTime(String path) throws KeeperException, InterruptedException{
        Stat stat = zookeeper.exists(path, false);
        return String.valueOf(stat.getCtime());
    }

    /**
     * 获取某个路径下孩子的数量
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public Integer getChildrenNum(String path) throws KeeperException, InterruptedException{
        int childrenNum = zookeeper.getChildren(path, false).size();
        return childrenNum;
    }

    /**
     * 关闭连接
     * @throws InterruptedException
     */
    public void closeConnection() throws InterruptedException{
        if (zookeeper != null) {
            zookeeper.close();
        }
    }
}
