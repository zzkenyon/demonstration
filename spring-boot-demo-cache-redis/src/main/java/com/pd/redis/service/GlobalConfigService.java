package com.pd.redis.service;

import com.pd.redis.bean.ConfigBean;

public interface GlobalConfigService {
    void updateConfig(ConfigBean config);
}
