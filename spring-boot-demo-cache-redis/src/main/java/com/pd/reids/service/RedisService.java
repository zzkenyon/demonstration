package com.pd.reids.service;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/11/29 13:56
 */
public interface RedisService {

    String get(String key);
    void set(String key,String val);
}
