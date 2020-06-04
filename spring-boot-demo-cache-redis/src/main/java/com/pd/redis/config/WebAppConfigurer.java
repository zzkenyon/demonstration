package com.pd.redis.config;

import com.pd.redis.limiter.LimiterInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/4 17:12
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个，这里选择拦截所有请求地址，进入后判断是否有加注解即可
        registry.addInterceptor(limiterInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public LimiterInterceptor limiterInterceptor(){
        return new LimiterInterceptor();
    }
}
