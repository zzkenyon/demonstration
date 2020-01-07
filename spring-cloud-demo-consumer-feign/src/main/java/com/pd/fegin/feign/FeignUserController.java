package com.pd.fegin.feign;

import com.pd.fegin.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/2 11:04
 */
@RestController
public class FeignUserController {
    private UserServiceFeignClient feignClient;
    @Autowired
    public void setFeignClient(UserServiceFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @GetMapping("/user/{id}")
    public User get(@PathVariable("id") Long id){
        return feignClient.getUser(id);
    }

}
