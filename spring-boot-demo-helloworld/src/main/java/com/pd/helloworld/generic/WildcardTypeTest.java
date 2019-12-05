package com.pd.helloworld.generic;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-12-5 20:25
 */

import lombok.extern.slf4j.Slf4j;

import java.util.List;


import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/**
 * WildcardType并不属于Java-Type中的一钟；例如：List< ? extends Number> 和 List< ? super Integer>；
 */
@Slf4j
public class WildcardTypeTest {
    private List< ? extends Number> a;

    private List< ? super String> b;

    private Class<?> aClass;

    private static void printWildcardType(WildcardType wildcardType) {
        for (Type type : wildcardType.getUpperBounds()) {
            log.info("上界：" + type);
        }
        for (Type type : wildcardType.getLowerBounds()) {
            log.info("下界：" + type);
        }
    }
}
