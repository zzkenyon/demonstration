package com.pd.starter.config;

import com.pd.starter.properties.DemoProperties;
import com.pd.starter.services.DemoService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2019-11-25 22:22
 */
@Configuration
@EnableConfigurationProperties(DemoProperties.class)
@ConditionalOnProperty(prefix = "demo",name = "isopen",havingValue = "true")
public class DemoConfig {
    @Resource
    private DemoProperties properties;

    @Bean
    public DemoService service(){
        return new DemoService(properties.getSayWhat(),properties.getToWho());
    }

}
