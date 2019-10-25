package com.pd.properties.config;

import com.pd.properties.properties.DeveloperProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {
    @Bean
    public DeveloperProperties developerProperties(){
        return new DeveloperProperties();
    }

    @Bean
    @ConfigurationPropertiesBinding
    public WeightConvert weightConvert(){
        return new WeightConvert();
    }
}
