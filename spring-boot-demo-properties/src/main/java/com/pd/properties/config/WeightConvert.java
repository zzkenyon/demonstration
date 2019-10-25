package com.pd.properties.config;


import com.pd.properties.bean.Weight;
import org.springframework.core.convert.converter.Converter;

/**
 * 自定义转换器，需要注册到容器中
 * 将字符串转换成自定义的重量单位Weight
 */
public class WeightConvert implements Converter<String, Weight> {
    @Override
    public Weight convert(String s) {
        /*
        ...
         */
        return null;
    }
}
