package com.pd.redis;

import com.pd.redis.bean.ConfigBean;
import com.pd.redis.service.GlobalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

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
