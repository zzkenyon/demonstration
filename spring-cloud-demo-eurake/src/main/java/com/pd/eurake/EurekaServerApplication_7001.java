package com.pd.eurake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020-1-1 12:40
 */
@SpringBootApplication
@EnableEurekaServer//标识是eureka服务端
public class EurekaServerApplication_7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication_7001.class);
    }
}
