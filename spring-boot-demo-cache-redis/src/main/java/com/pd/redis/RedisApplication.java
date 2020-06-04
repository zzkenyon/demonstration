package com.pd.redis;

import com.pd.redis.bean.User;
import com.pd.redis.limiter.AccessLimiter;
import com.pd.redis.service.RedisService;
import com.pd.redis.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class RedisApplication {
    @Resource
    private UserService service;
    @Resource
    private RedisService redisService;
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @AccessLimiter(perSec = 1)
    @GetMapping(value = "/user/{id}")
    public User queryById(@PathVariable("id") Long id){
        return service.get(id);
    }

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
