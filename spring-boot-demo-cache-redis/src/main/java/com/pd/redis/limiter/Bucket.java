package com.pd.redis.limiter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/4 15:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bucket implements Serializable {
    private Long lastUpdate;
    private long token;
}
