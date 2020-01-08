package com.pd.helloworld.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/8 14:31
 */

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if(beanFactory instanceof DefaultListableBeanFactory){
            System.out.println("我是自定义的beanFactory后置处理器");
        }
    }
}
