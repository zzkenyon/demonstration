package com.pd.actuator.metric;

import com.pd.actuator.metric.client.MetricsClient;
import com.pd.actuator.metric.client.impl.SpringMetricsClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaozhengkang@cetiti.com
 * @description
 * @date 2019/11/25 15:27
 */
@Configuration
public class MetricConfig {
    @Bean
    @ConditionalOnMissingBean
    public MetricsClient springMetricsClient() {
        return new SpringMetricsClient();
    }
    @Bean
    public SpringMetricsRegistry springMetricsRegister() {
        return new SpringMetricsRegistry();
    }
}

