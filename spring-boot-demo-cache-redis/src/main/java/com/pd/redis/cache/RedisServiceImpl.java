package com.pd.redis.cache;

import com.pd.redis.common.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * @author zhaozhengkang
 * @description
 * @date 2019/11/29 13:59
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate<String, String> template;
    @Resource
    private HashOperations<String, String, Object> hashOperations;

    @Override
    public String get(String key) {
        return template.opsForValue().get(key);
    }
    @Override
    public void set(String key,String val){
        template.opsForValue().set(key,val);
    }

    @Override
    public void hset(String key, Map<String, User> userMap) {
        hashOperations.putAll(key,userMap);
    }

    @Override
    public User hget(String key, String hKey) {
        return (User)hashOperations.get(key,hKey);
    }

    @Resource
    private SetOperations<String,Object> setOperations;
    @Override
    public void sset(String key, User...users) {
        setOperations.add(key,users);
    }

    @Override
    public Set<User> ssget(String key) {
        Set<Object> members = setOperations.members(key);
        Set<User> users = new HashSet<>();
        for(Object o : members){
            users.add((User)o);
        }
        return users;
    }

}
