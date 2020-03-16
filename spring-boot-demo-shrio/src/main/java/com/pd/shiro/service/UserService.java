package com.pd.shiro.service;

import com.pd.shiro.model.User;

import java.util.Set;

/**
 * @Author: Jay_Liu
 * @Description:
 * @Date: Created in 21:21 2018/3/24 0024
 * @Modified By:
 */
public interface UserService {

    /**
     * 创建账户
     * @param user
     * @return
     */
     User createUser(User user);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
     void changePassword(Long userId, String newPassword);

    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
     void correlationRoles(Long userId, Long... roleIds);

    /**
     * 移除用户-角色关系
     * @param userId
     * @param roleIds
     */
     void removeRoles(Long userId, Long... roleIds);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
     User findUserByUserName(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
     Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
     Set<String> findPermissions(String username);
}
