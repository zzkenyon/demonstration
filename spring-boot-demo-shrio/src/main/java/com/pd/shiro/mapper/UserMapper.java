package com.pd.shiro.mapper;

import com.pd.shiro.model.Permission;
import com.pd.shiro.model.Role;
import com.pd.shiro.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

/**
 * @Author: Jay_Liu
 * @Description:
 * @Date: Created in 21:19 2018/3/24 0024
 * @Modified By:
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询用户实体
     * @param userName 用户名
     * @return
     */
    @Select("SELECT uid,user_name,password FROM user WHERE user_name = #{userName}")
    @Results(id = "userMap",value = {
            @Result(id = true, column = "uid", property = "uid"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "password",column = "password"),
            @Result(property = "roles", column = "uid",
                    many = @Many(select = "com.pd.shiro.mapper.UserMapper.findRolesByUserId"))
    })
    User findByUserName(@Param("userName") String userName);

    @Select("SELECT rid,role_name FROM role WHERE rid IN (SELECT rid FROM user_role WHERE uid=#{uid})")
    @Results(id = "roleMap",value = {
            @Result(id = true,column = "rid",property = "rid"),
            @Result(property = "roleName",column = "role_name"),
            @Result(property = "permissions",column = "rid",
            many = @Many(select = "com.pd.shiro.mapper.UserMapper.findPermissionsByRid"))
    })
    Set<Role> findRolesByUserId(@Param("uid") Integer uid);

    @Select("SELECT * FROM permission WHERE pid IN (SELECT pid FROM role_permission WHERE rid = #{rid}")
    Set<Permission> findPermissionsByRid(@Param("rid") Integer rid);
}
