package com.pd.helloworld.di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-24 15:27
 */
@Component
public class AuthorInfo {
    @Value("zhaopanda")
    private String name ;
    @Value("2")
    private int age;
    @Value("flash")
    private String present;

}
