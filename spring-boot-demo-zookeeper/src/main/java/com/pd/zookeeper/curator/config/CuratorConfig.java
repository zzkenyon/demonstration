package com.pd.zookeeper.curator.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-11-29 9:03
 */
@Configuration
@EnableConfigurationProperties(CuratorProperties.class)
public class CuratorConfig {
    private final CuratorProperties properties;
    @Autowired
    public CuratorConfig(CuratorProperties properties){
        this.properties = properties;
    }

    @Bean
    public CuratorFramework curatorFramework(){
        RetryPolicy policy = new ExponentialBackoffRetry(properties.getTimeOut(),properties.getRetry());
        CuratorFramework client = CuratorFrameworkFactory.newClient(properties.getUrl(),policy);
        client.start();
        return client;
    }
}
