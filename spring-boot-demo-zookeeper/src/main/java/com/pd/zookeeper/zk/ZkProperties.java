package com.pd.zookeeper.zk;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2019-11-28 14:44:23
 */
@Data
@ConfigurationProperties(prefix = "zk")
public class ZkProperties {
    /**
     * zookeeper服务器 ip:port
     */
    private String host;
    /**
     * 会话超时时间，单位ms，默认值2000ms
     */
    private Integer sessionTimeOut = 2000;
}
