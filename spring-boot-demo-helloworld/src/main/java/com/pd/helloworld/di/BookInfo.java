package com.pd.helloworld.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-23 14:41
 */
@Primary
@Component("flash panda")
public class BookInfo {

    @Value("flash panda")
    private String name;

    @Value("117.0f")
    private float price;

    @Autowired
    public void setAuthor(AuthorInfo author) {
        this.author = author;
    }
    private AuthorInfo author;


    public void init(){

    }
}
