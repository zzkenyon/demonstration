package com.pd.hibernate.service;


import com.pd.hibernate.domain.Blog;

import java.util.List;

public interface IBlogService {
     List<Blog> queryBlog();

     void addBlog(Blog blog);
}
