package com.pd.returnformart.bean;

import lombok.Data;

/**
 * @author zhaozhengkang@cetiti.com
 * @description
 * @date 2019/11/20 14:56
 */
@Data
public class ArgumentInvalidResult {
    private String field;
    private Object rejectedValue;
    private String message;
}

