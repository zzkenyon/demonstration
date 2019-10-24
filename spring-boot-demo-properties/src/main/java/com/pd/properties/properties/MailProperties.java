package com.pd.properties.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.mail")
@Component
@Data
public class MailProperties {
    private Boolean enable = Boolean.TRUE;
    private String defaultSubject;
}
