package com.pd.kafka.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-12-25 15:43
 */
@Data
public class User implements Serializable {
    private Long id;

    private String name;

    private Integer age;
    /**
     * transient 关键字修饰的字段不会被序列化
     */
    private transient String desc;
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", desc='" + desc + '\'' +
                '}';
    }
}
