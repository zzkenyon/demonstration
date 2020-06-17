package com.pd.zuul.auth.controller;

import com.pd.zuul.auth.model.User;
import com.pd.zuul.auth.service.UserService;
import com.pd.zuul.common.bean.RestResponseEntity;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/15 10:49
 */
@RestController
public class UserController {
    private UserService service;
    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @PostMapping("/user")
    @RequiresRoles("admin")
    public ResponseEntity addUser(@RequestBody User user){
        return RestResponseEntity.ok(service.createUser(user));
    }

}
