package com.pd.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2019-11-25 22:19
 */
@ConfigurationProperties(prefix = "demo")
@Data
public class DemoProperties {
    private String sayWhat;
    private String toWho;
}
