package com.pd.jdbc;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-05-18 11:10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private BlogService service;

    @Test
    public void jdbcTest(){
        List<Blog> blogs = service.listBlog();
        System.out.println(blogs);
    }

    @Test
    public void testDeleteAll(){
        System.out.println(service.deleteAll());
    }
}
