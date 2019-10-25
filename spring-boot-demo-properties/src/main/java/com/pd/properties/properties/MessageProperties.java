package com.pd.properties.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@ConfigurationProperties(prefix = "app.message",ignoreInvalidFields = true)
@Component
@Data
public class MessageProperties {
    private String from;
    private DataSize size;
    private Duration time;
}
