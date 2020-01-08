package com.pd.helloworld.context;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/8 13:58
 */
@Order(1000000)
public class MyContextInitializer implements ApplicationContextInitializer<GenericApplicationContext> {
    @Override
    public void initialize(GenericApplicationContext applicationContext) {
        applicationContext.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor());
        List<BeanFactoryPostProcessor> postProcessors = applicationContext.getBeanFactoryPostProcessors();
        System.out.println("beanFactoryPostProcessor count is " + postProcessors.size());
        for (BeanFactoryPostProcessor postProcessor : postProcessors){
            System.out.println(postProcessor.getClass());
        }
        applicationContext.addBeanFactoryPostProcessor(new MyBeanDefinitionRegistryPostProcessor());
        applicationContext.addApplicationListener(new MyApplicationListener());
    }
}
