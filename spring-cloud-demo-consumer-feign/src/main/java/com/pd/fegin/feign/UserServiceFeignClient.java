package com.pd.fegin.feign;

import com.pd.fegin.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/2 10:57
 */
@FeignClient(value = "user-provider")
public interface UserServiceFeignClient {
    @GetMapping(value = "/provider/user/{id}")
    User getUser(@PathVariable("id")Long id);
}
