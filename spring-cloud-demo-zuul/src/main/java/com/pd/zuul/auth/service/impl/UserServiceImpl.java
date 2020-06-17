package com.pd.zuul.auth.service.impl;


import com.pd.zuul.auth.mapper.UserMapper;
import com.pd.zuul.auth.model.User;
import com.pd.zuul.auth.service.TokenService;
import com.pd.zuul.auth.service.UserService;
import com.pd.zuul.auth.realm.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Set;

/**
 * @Author: Jay_Liu
 * @Description:
 * @Date: Created in 21:22 2018/3/24 0024
 * @Modified By:
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final int LOGIN_ERROR_TIME = 10 * 60;
    private static final String REDIS_LOCK_PREFIX = "user:login-error";
    @Autowired
    private ValueOperations<String, Object> redisOps;
    @Resource
    private UserMapper userMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    public Integer createUser(User user) {
        passwordHelper.encryptPassword(user);
        return userMapper.insertOne(user);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {

    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public void removeRoles(Long userId, Long... roleIds) {

    }

    @Override
    @Transactional(readOnly = true )
    public User findUserByUserName(String username) {
        return userMapper.findUserByUserName(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        return null;
    }

    @Override
    public Set<String> findPermissions(String username) {
        return null;
    }

    @Override
    public String login(User userInfo, HttpServletRequest request) {
        String ip = getClientIp(request);
        userInfo.setLastLoginIp(ip);
        userInfo.setLastLoginTime(new Date());
        if(userInfo.getLoginCount() == null ){
            userInfo.setLoginCount(1);
        }else {
            userInfo.setLoginCount(userInfo.getLoginCount()+1);
        }
        String uid = String.valueOf(userInfo.getUid());
        userMapper.modify(userInfo);
        return tokenService.generateAccessToken(uid);
    }

    @Override
    public User findUserByUid(int userId) {
        return userMapper.findUserByUid(userId);
    }



    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
