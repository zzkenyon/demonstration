package com.pd.redis.globalconf;

import lombok.Data;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/11/29 16:27
 */
@Data
public class ConfigBean {
    String host;
    String port;
    Boolean isOpen;
}
