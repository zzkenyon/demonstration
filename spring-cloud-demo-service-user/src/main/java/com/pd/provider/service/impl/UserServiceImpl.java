package com.pd.provider.service.impl;

import com.pd.provider.bean.User;
import com.pd.provider.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020-1-1 15:24
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Value("${server.port}")
    private int port;
    /**
     * 模拟数据库
     */
    private static final Map<Long, User> DATABASES = new HashMap<>();

    /**
     * 初始化数据
     */
    static {
        DATABASES.put(1L, new User(1L, "zhaozhengkang",""));
        DATABASES.put(2L, new User(2L, "panjiaping",""));
        DATABASES.put(3L, new User(3L, "zhaopanda",""));
        DATABASES.put(4L, new User(4L, "zhanghuigen",""));
        DATABASES.put(5L, new User(5L, "zhenghuanbo",""));
        DATABASES.put(6L, new User(6L, "lishaoli",""));
        DATABASES.put(7L, new User(7L, "qita",""));
    }
    /**
     * 获取用户
     *
     * @param id key值
     * @return 返回结果
     */
    @Override
    public User get(Long id) {
        // 我们假设从数据库读取
        log.info("查询用户【id】= {}", id);
        User u = DATABASES.get(id);
        u.setServiceId("user-provider:"+port);
        return DATABASES.get(id);
    }

    /**
     * 删除
     *
     * @param id key值
     */
    @Override
    public void delete(Long id) {
        DATABASES.remove(id);
        log.info("删除用户【id】= {}", id);
    }

    @Override
    public User edit(List<User> newUser) {
        return newUser.get(0);
    }
}
