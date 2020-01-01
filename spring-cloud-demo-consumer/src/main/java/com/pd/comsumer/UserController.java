package com.pd.comsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020-1-1 14:53
 */
@RestController
@RequestMapping("/consumer")
public class    UserController {

    //多个方法调用只需改一处就ok
    public static  final String URL_PREFIX = "http://localhost:8001";

    private RestTemplate restTemplate;
    @Autowired
    public void setRestTemplate(RestTemplate template){
        this.restTemplate = template;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id")Long id){
        //调用远程服务 http请求
        String url = URL_PREFIX+"/provider/user/"+id;
        return restTemplate.getForObject(url,User.class );
    }
}
