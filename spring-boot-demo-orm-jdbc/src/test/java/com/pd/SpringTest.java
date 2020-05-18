package com.pd;

import com.pd.jdbc.Blog;
import com.pd.jdbc.BlogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-05-18 11:10
 */
@SpringBootTest
public class SpringTest {
    @Resource
    private BlogService service;

    @Test
    public void jdbcTest(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.start();

        List<Blog> blogs = service.listBlog();
        System.out.println(blogs);
    }
}
