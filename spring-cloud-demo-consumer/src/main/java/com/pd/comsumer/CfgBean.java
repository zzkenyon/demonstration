package com.pd.comsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020-1-1 14:50
 */
@Configuration
public class CfgBean {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

