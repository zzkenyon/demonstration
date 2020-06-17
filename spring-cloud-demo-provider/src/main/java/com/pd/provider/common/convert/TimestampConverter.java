package com.pd.provider.common.convert;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

/**
 * TimestampConverter
 *
 * @author liuzhaoqi@cetiti.com
 * @since 0.1.0
 */
public class TimestampConverter implements Converter<String, Timestamp> {

    @Override
    public Timestamp convert(String source) {

        return new Timestamp(Long.valueOf(source));
    }
}
