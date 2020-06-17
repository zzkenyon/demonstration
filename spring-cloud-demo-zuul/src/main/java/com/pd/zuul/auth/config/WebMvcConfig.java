package com.pd.zuul.auth.config;

import com.pd.zuul.common.convert.TimestampConverter;
import com.pd.zuul.common.convert.UniversalEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.List;
import java.util.Properties;

/**
 * WebMvcConfig
 *
 * @author liuzhaoqi@cetiti.com
 * @since 0.1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VersionInterceptor());
    }*/

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new UniversalEnumConverterFactory());
        registry.addConverter(new TimestampConverter());
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties shiroExceptionMappings = new Properties();
        shiroExceptionMappings.put("org.apache.shiro.authz.UnauthorizedException","/unauthorized");
        shiroExceptionMappings.put("org.apache.shiro.authz.UnauthenticatedException","/unauthenticated");
        resolver.setExceptionMappings(shiroExceptionMappings);
        resolvers.add(resolver);
    }
}
