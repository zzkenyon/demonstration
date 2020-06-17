package com.pd.redis.limiter;

import com.pd.redis.cache.UserService;
import com.pd.redis.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/5 09:31
 */
@RestController
public class LimiterTestController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @AccessLimiter(perSec = 1)
    @GetMapping(value = "/user/{id}")
    public User queryById(@PathVariable("id") Long id){
        return userService.get(id);
    }
}
