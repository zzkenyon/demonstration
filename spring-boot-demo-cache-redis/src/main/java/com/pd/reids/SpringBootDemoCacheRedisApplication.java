package com.pd.reids;

import com.pd.reids.bean.User;
import com.pd.reids.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
public class SpringBootDemoCacheRedisApplication {
    @Resource
    private UserService service;
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoCacheRedisApplication.class, args);
    }
    @GetMapping(value = "/user/{id}")
    public User queryById(@PathVariable("id") Long id){
        return service.get(id);
    }

    @PutMapping(value = "/useradd")
    public User save(@RequestBody User user){
        service.saveOrUpdate(user);
        return user;
    }
}
