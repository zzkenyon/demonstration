package com.pd.helloworld.di;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-24 17:04
 */
public class XmlContextTest {
    @Test
    public void testXmlContext(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        context.getBean("dataSource");
    }
}
