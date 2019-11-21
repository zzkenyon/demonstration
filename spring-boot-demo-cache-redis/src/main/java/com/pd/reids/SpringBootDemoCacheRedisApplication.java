package com.pd.reids;

import com.pd.reids.bean.User;
import com.pd.reids.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
public class SpringBootDemoCacheRedisApplication {
    @Resource
    private UserService service;
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoCacheRedisApplication.class, args);
    }

    @GetMapping(value = "/user")
    public User queryById(@Param("id") Long id){
        return service.get(id);
    }

    @PutMapping(value = "/useradd")
    public User save(@RequestBody User user){
        service.saveOrUpdate(user);
        return user;
    }
}
