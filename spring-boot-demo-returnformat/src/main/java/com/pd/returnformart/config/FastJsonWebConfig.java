package com.pd.returnformart.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter.APPLICATION_JAVASCRIPT;

/**
 * @author zhaozhengkang@cetiti.com
 * @description
 * @date 2019/11/20 14:31
 */
@Configuration
public class FastJsonWebConfig implements WebMvcConfigurer {


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        // fastJson会对HTTP的content-type进行处理，默认接受所有类型的请求
        // 因此如果 request accept application/json 类型(对于ajax请求的情况) 则会响应 application/json类型
        // 如果在页面url直接访问，并且*的权重较低，则可能会响应text/html类型, Postman调试时需注意, 但不影响使用
        // 因此在这里设置fastJson支持的响应类型，避免在某些情况下响应为text/html

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(APPLICATION_JAVASCRIPT);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(0,fastConverter);
    }
}