package com.pd.shiro.service;

import com.pd.shiro.mapper.UserMapper;
import com.pd.shiro.model.User;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Set;

/**
 * @Author: Jay_Liu
 * @Description:
 * @Date: Created in 21:22 2018/3/24 0024
 * @Modified By:
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public void changePassword(Long userId, String newPassword) {

    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        return null;
    }

    @Override
    public Set<String> findPermissions(String username) {
        return null;
    }
}
