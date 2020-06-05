package com.pd.redis.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhaozhengkang@cetiti.com
 * @description
 * @date 2019/11/21 12:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;

    public String toString() {
        return "id = " + id + " name = " + name;
    }
}
