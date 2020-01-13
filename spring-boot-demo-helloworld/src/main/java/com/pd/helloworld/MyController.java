package com.pd.helloworld;

import com.pd.helloworld.aware.MyResourceLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/13 10:43
 */
@RestController
public class MyController {
    private MyResourceLoaderAware awareService;

    @Autowired
    public void setAwareService(MyResourceLoaderAware awareService) {
        this.awareService = awareService;
    }

    @GetMapping("/resourceprint")
    public String resource(){
        awareService.resourceLoaderTest();
        return "nice";
    }
}
