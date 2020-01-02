package com.pd.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020-1-1 15:19
 */
@SpringBootApplication
@EnableEurekaClient //表示是eureka的客户端
public class UserProvider_8002 {
    public static void main(String[] args) {
        SpringApplication.run(UserProvider_8002.class);
    }
}
