package com.pd.es.auditlog;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/10 10:39
 */
public class Test {
    public static void main(String[] args) {
        User u = new User("zzk",30);
        Object o = u;
        System.out.println("o  is   " + JSON.toJSONString(o));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class User{
    private String name;
    private int age;
}

