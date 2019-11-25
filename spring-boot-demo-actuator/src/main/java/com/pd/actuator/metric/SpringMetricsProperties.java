package com.pd.actuator.metric;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhaozhengkang@cetiti.com
 * @description 属性配置类
 * @date 2019/11/25 15:30
 */
@Data
@Component
@ConfigurationProperties(prefix = "metrics.spring")
public class SpringMetricsProperties {
    /**
     * 默认为true，使用spring埋点方式
     */
    private boolean enabled = true;
    /**
     * 使用default metrics client 时是否静音模式，默认打开
     */
    private boolean muteDefaultClient = true;
    /**
     * 使用default metrics client 的打印时间间隔
      */
    private int defaultClientPeriod = 10000;
}
