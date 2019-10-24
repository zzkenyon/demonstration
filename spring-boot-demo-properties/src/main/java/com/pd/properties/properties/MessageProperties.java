package com.pd.properties.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

@ConfigurationProperties(prefix = "app.message")
@Component
@Data
public class MessageProperties {
    private String from;
    private DataSize size;
}
