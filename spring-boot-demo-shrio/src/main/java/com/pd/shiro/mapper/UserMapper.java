package com.pd.shiro.mapper;

import com.pd.shiro.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Jay_Liu
 * @Description:
 * @Date: Created in 21:19 2018/3/24 0024
 * @Modified By:
 */
@Mapper
public interface UserMapper {

    User findByUsername(@Param("username") String username);
}
