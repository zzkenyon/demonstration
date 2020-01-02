package com.pd.fegin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020-1-1 15:34
 */
@SpringBootApplication
@EnableFeignClients
public class UserConsumerFeign {
    public static void main(String[] args) {
        SpringApplication.run(UserConsumerFeign.class);
    }
}
