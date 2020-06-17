package com.pd.provider.controller;

import com.pd.provider.bean.User;
import com.pd.provider.common.bean.RestResponseEntity;
import com.pd.provider.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020-1-1 15:19
 */
@RestController
public class UserController {

    private IUserService userService;
    @Autowired
    public void setUserService(IUserService service){
        this.userService = service;
    }

    @GetMapping("/user/{id}") //user/1
    public User getUser(@PathVariable("id") Long id) {
        // 正常应该调用service获取用户,现在模拟一下
        return userService.get(id);
    }

    @PutMapping(value = "/user/edit",produces = "application/json;charset=utf-8")
    public ResponseEntity editUser(@RequestBody List<User> newUsers){
        return RestResponseEntity.ok(userService.edit(newUsers));
    }
}
