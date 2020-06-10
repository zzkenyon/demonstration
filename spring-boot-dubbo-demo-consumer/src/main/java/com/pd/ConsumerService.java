package com.pd;

import com.pd.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/10 16:39
 */
public class ConsumerService {
    @Reference
    HelloService helloService;

    public void sayHello(){
        System.out.println(helloService.hello("zhaozhengkang"));
    }

}
