package com.pd.provider.controller;

import com.pd.provider.bean.User;
import com.pd.provider.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020-1-1 15:19
 */
@RestController
@RequestMapping("/provider")
public class UserController {

    private IUserService userService;
    @Autowired
    public void setUserService(IUserService service){
        this.userService = service;
    }

    @RequestMapping("/user/{id}") //user/1
    public User getUser(@PathVariable("id") Long id) {
        // 正常应该调用service获取用户,现在模拟一下
        return userService.get(id);
    }
}
