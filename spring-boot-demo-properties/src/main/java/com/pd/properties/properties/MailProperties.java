package com.pd.properties.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "app.mail",ignoreInvalidFields = true)
@Component
@Data
public class MailProperties {
    private Boolean enable = Boolean.TRUE;
    private String defaultSubject;
    /**
     * 获取列表类型属性
     */
    private List<String> smtpServer;

    private String from;
    private DataSize size;
    private Duration time;
    private final Detail detail = new Detail();
    private final Map<String,String> properties = new HashMap<>();

    @Data
    public static class Detail{
        private String cTime;
        private String uTime;
        private Boolean isNow;
    }
}
