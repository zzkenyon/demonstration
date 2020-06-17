package com.pd.zuul.auth.service;

import com.pd.zuul.auth.model.User;

import javax.servlet.http.HttpServletRequest;
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
     Integer createUser(User user);

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


    /**
     * 用户登录过程中，生成accessToken，修改数据库相关字段如最后登录时间等
     * @param user
     * @param request
     * @return
     */
    String login(User user, HttpServletRequest request);

    /**
     * 根据用户id
     * @param userId
     * @return
     */
    User findUserByUid(int userId);
}
