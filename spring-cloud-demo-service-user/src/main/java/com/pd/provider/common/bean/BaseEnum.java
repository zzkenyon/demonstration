package com.pd.provider.common.bean;
/**
        * BaseEnum
        * 与UniversalEnumConverterFactory配合, 用于get参数传递时，识别传递的枚举索引值，Springboot默认只能识别枚举字符串
        *
        * @author liuzhaoqi@cetiti.com
 * @since 0.1.0
        */
public interface BaseEnum {
    int getValue();
}
