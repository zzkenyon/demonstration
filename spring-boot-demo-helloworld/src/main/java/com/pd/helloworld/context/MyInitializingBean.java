package com.pd.helloworld.context;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-20 17:43
 */
@Component
public class MyInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
