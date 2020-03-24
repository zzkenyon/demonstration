package com.pd.zuul.auth.model;

import com.pd.zuul.common.bean.BaseEnum;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/3/23 11:13
 */
@Getter
public enum LoginType implements BaseEnum {
    /**
     * LOGIN_NORMAL 用户名登录
     * LOGIN_SMS 短信登录
     * LOGIN_LDAP LDAP登录
     */
    LOGIN_NORMAL(0), LOGIN_SMS(1), LOGIN_LDAP(2);

    LoginType(int v){
        this.value = v;
    }

    private int value;

    /**
     * 配合UniversalEnumConverterFactory用于get传参时的枚举转换
     */
    private static Map<Integer, LoginType> valueMap = new HashMap<>();

    static {
        for(LoginType type : LoginType.values()) {
            valueMap.put(type.value, type);
        }
    }
}
