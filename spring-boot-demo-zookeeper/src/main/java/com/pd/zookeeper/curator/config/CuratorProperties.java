package com.pd.zookeeper.curator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-11-29 8:59
 */
@Data
@ConfigurationProperties(prefix = "curator")
public class CuratorProperties {
    /**
     * 连接地址
     */
    private String url;
    /**
     * 超时时间默认2000ms
     */
    private int timeOut = 2000;
    /**
     * 重试次数 默认3次
     */
    private int retry =3;
}
