package com.pd.helloworld.di;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-23 14:40
 */
@Component("wodemama")
public class BookInfoFactoryBean implements FactoryBean<BookInfo> {
    @Override
    public BookInfo getObject() throws Exception {
        return new BookInfo();
    }

    @Override
    public Class<?> getObjectType() {
        return BookInfo.class;
    }

}
