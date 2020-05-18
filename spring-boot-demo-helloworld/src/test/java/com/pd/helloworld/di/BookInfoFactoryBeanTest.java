package com.pd.helloworld.di;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-23 14:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest

public class BookInfoFactoryBeanTest {
    @Autowired
    private DiTestService service;

    @Test
    public void testGetBean() throws Exception {
        Object o = service.getBean("flash panda");
    }
}
