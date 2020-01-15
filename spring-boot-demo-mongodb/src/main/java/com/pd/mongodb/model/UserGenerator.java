package com.pd.mongodb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-01-14 16:00
 */
public class UserGenerator {

    public static User getUser(){
        List<User> lovers = new ArrayList<>();
        lovers.add(new User("zhaopanda",1,"nanjing",null,null));
        lovers.add(new User("panjiaping",28,"nanjing",null,null));
        User res = new User("zhaozhengkang",30,"hangzhou","nuaa", lovers);
        return res;
    }
}
