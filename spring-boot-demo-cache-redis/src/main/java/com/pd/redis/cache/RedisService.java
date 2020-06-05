package com.pd.redis.cache;

import com.pd.redis.common.User;

import java.util.Map;
import java.util.Set;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/11/29 13:56
 */
public interface RedisService {

    String get(String key);
    void set(String key,String val);

    void hset(String key, Map<String, User> userMap);
    User hget(String key,String hKey);

    void sset(String key, User...users);
    Set<User> ssget(String key);
}
