package com.pd;

import com.pd.service.HelloService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/10 15:47
 */
@Service
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hello" + name;
    }
}
