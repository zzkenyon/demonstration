package com.pd.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-01-14 15:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private String name;

    private Integer sex;

    private String addr;

    private String school;

    private List<User> lovers;
}
