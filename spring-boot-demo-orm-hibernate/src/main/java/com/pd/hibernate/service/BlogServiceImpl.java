package com.pd.hibernate.service;


import com.pd.hibernate.dao.BlogDao;
import com.pd.hibernate.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public List<Blog> queryBlog()
    {
        return blogDao.findAll();
    }

    @Override
    public void addBlog(Blog blog)
    {
        blogDao.save(blog);
    }
}
