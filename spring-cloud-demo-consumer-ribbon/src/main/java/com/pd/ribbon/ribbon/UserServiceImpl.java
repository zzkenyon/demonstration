package com.pd.ribbon.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pd.ribbon.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/7 9:55
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    //多个方法调用只需改一处就ok
    public static  final String URL_PREFIX = "http://USER-PROVIDER";

    private RestTemplate restTemplate;
    @Autowired
    public void setRestTemplate(RestTemplate template){
        this.restTemplate = template;
    }

    @Override
//    @HystrixCommand(fallbackMethod = "getUserFallback")
    public User getUser(Long id) {
        //调用远程服务 http请求
        String url = URL_PREFIX+"/user/"+id;
        return restTemplate.getForObject(url,User.class);
    }

    public User getUserFallback(Long id){
        log.info("断路器已打开");
        return new User(0L,"null","null");
    }
}
