package com.pd.zookeeper;

import com.pd.zookeeper.zk.ZkProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ZkProperties.class)
public class SpringBootDemoZookeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoZookeeperApplication.class, args);
    }
}
