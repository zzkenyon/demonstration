package com.pd.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-05-18 12:51
 */
@Service
public class BlogService {
    @Autowired
    JdbcTemplate template;
    public List<Blog> listBlog(){
        String sql = "select * from blog";
        return template.query(sql,new BaseRowMapper<>(Blog.class));
    }
}
