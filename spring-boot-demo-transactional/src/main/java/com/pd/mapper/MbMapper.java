package com.pd.mapper;

import com.pd.model.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/21 11:36
 */
@Mapper
public interface MbMapper {
    @Select("select * from blog")
    List<Blog> queryBlogs();
}
