package com.pd.redis.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-20 15:45
 */
@RestController
public class LockTestController {
    @Autowired
    LockTestService service;
    @GetMapping(value = "/test-lock")
    public String lock(){
        service.print();
        return "success";
    }
}
