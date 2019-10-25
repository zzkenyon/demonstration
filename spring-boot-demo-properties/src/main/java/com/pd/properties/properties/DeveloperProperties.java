package com.pd.properties.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * 使用value注解获取属性
 * 需要注册到spring容器中，此处使用在@Configuration类中注册bean的方式
 */
@Data
public class DeveloperProperties {
    @Value("${app.developer.name}")
    private String name;

    @Value("${app.developer.mail-address}")
    private String mailAddress;
}
