package com.pd.es;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-20 14:15
 */
@RestController
@Slf4j
public class EsTestController {

    @Resource
    EsTestService esTestService;
    @GetMapping(value = "/log-to-es")
    public String log(){
        esTestService.logToEs();
        return "success";
    }
}
