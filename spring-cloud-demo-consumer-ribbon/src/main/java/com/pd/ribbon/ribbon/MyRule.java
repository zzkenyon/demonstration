package com.pd.ribbon.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/7 15:11
 */
public class MyRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    public Server choose(ILoadBalancer lb,Object key){
        if (lb == null) {
            return null;
        }
        Server server = null;
        while(server==null){
            List<Server> allList=lb.getAllServers();
            System.out.println(allList);
            int index=0;
            server=allList.get(index);
        }
        return server;
    }
}
