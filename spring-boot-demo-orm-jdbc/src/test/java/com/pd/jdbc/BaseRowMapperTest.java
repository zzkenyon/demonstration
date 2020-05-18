package com.pd.jdbc;


import org.junit.Test;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-05-18 14:22
 */
public class BaseRowMapperTest {
    @Test
    public void camelTo_test() {
        BaseRowMapper<String> mapper = new BaseRowMapper<>();
        System.out.println(mapper.camelTo_("myName"));
    }
}
