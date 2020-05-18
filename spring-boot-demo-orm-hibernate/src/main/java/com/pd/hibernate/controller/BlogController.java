package com.pd.hibernate.controller;

import com.pd.hibernate.domain.Blog;
import com.pd.hibernate.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    // http://127.0.0.1:9096/blog/query
    // 注意是mybatis数据库
    @RequestMapping("/query")
    public List<Blog> queryUser()
    {
        List<Blog> list = blogService.queryBlog();
        return list;
    }

    // http://127.0.0.1:9096/blog/save
    // 注意是mybatis数据库
    @RequestMapping("/save")
    public void addBlog()
    {
        Blog blog = new Blog();
        blog.setBid(8000008);
        blog.setName("Redis从入门到改行");
        blog.setAuthorId(1001);
        blogService.addBlog(blog);
    }
}