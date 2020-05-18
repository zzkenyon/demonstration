package com.pd.helloworld.di;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-23 14:56
 */
@Service
public class DiTestService implements ApplicationContextAware {
    private ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public Object getBean(String beanName){
        return context.getBean(beanName);
    }
}
