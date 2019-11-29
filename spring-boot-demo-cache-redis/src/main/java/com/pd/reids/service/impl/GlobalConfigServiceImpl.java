package com.pd.reids.service.impl;

import com.alibaba.fastjson.JSON;
import com.pd.reids.bean.ConfigBean;
import com.pd.reids.service.GlobalConfigService;
import com.pd.reids.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/11/29 17:21
 */
@Service
@Slf4j
public class GlobalConfigServiceImpl implements GlobalConfigService {
    @Autowired
    private RedisService redisService;

    @Override
    @Transactional
    public void updateConfig(ConfigBean config){
        System.out.println("新配置入库");
        String confJson = JSON.toJSON(config).toString();
        redisService.set("global_config",confJson);
    }
}
