package com.pd.reids;

import com.alibaba.fastjson.JSON;
import com.pd.reids.bean.ConfigBean;
import com.pd.reids.service.GlobalConfigService;
import com.pd.reids.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/11/29 14:21
 */
@RestController
@EnableScheduling
public class GlobalConfigController {
    @Autowired
    private GlobalConfigService configService;

    /*//向redis消息队列index通道发布消息
    @Scheduled(fixedRate = 3000)
    void sendMessage(){
        redisService.set("global_config", "werasdfa");
    }
*/
    @PutMapping("/global_config")
    public String updateConfig(@RequestBody ConfigBean config){
        configService.updateConfig(config);
        return "test";
    }

}
