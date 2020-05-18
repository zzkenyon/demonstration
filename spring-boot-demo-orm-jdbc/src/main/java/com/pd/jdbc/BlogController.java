package com.pd.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-05-18 13:06
 */
@RestController
public class BlogController {
    @Autowired
    BlogService service;

    @GetMapping("/listBlog")
    public List<Blog> listBlog (){
        return service.listBlog();
    }
}
