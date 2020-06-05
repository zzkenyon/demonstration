package com.pd.rabbitmq.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-06-02 17:33
 */
@Data
@NoArgsConstructor
public class User implements Serializable {
    String name;
    String address;
    int age;

    public User(String zzk, String zjhz, int i) {
        this.name = zzk;
        this.address = zjhz;
        this.age = i;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}