package com.pd.redis.cache;

import com.pd.redis.common.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/5 09:29
 */
@RestController
public class UserController {
    @Resource
    private UserService service;
    @Resource
    private RedisService redisService;

    @PutMapping(value = "/useradd")
    public User save(@RequestBody User user){
        service.saveOrUpdate(user);
        return user;
    }

    @GetMapping("/hset")
    public String hSave(){
        Map<String,User> userMap = new HashMap<>();
        userMap.put("baba",new User(1L,"zhaozhengkang"));
        userMap.put("mama",new User(2L,"panjiaping"));
        userMap.put("son",new User(3L,"zhaopanda"));
        redisService.hset("home",userMap);
        return "nice";
    }

    @GetMapping("/hget/{hkey}")
    public User hget(@PathVariable("hkey")String hkey ){
        return redisService.hget("home",hkey);
    }

    @GetMapping("/sset")
    public String ssave(){
        User[] users = {new User(1L,"zhaozhengkang"),
                new User(2L,"panjiaping"),
                new User(3L,"zhaopanda")};
        redisService.sset("homeset",users);
        return "nice";
    }
}
