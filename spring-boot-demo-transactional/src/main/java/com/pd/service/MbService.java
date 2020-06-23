package com.pd.service;

import com.pd.mapper.MbMapper;
import com.pd.model.Blog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/21 11:36
 */
@Service
public class MbService {
    @Resource
    private MbMapper mbMapper;

    public List<Blog> queryBlogs(){
        return mbMapper.queryBlogs();
    }
}
