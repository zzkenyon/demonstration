package com.pd.zuul.auth.controller;

import com.pd.zuul.common.bean.RestResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/15 16:49
 */
@RestController
public class ShiroController {

    @GetMapping(value = "/unauthorized")
    public ResponseEntity unauthorized(){
        return RestResponseEntity.error(40111,"无访问权限", HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/logined")
    public ResponseEntity logined(){
        return RestResponseEntity.ok("已登录");
    }
}
