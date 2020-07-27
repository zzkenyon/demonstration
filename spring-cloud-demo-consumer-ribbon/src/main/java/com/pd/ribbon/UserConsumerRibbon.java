package com.pd.ribbon;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020-1-1 15:34
 */
@SpringBootApplication
//@EnableCircuitBreaker
public class UserConsumerRibbon {
    public static void main(String[] args) {
        SpringApplication.run(UserConsumerRibbon.class);
    }

}
