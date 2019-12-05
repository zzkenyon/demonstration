package com.pd.helloworld.generic;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/12/5 11:40
 */
@Slf4j
public class TypeVariableTest<T extends Number & Serializable, V> {
    /**
     * TypeVariable
     */
    private T key;

    /**
     * TypeVariable
     */
    private V value;

    /**
     * GenericArrayType V[]-> V TypeVariable 两种混合起来了
     */
    private V[] values;
    /**
     * 原始类型，不仅仅包含我们平常所指的类，还包括枚举、数组、注解等；
     * 基本类型，也就是我们所说的java的基本类型，即int,float,double等
     */
    private String str;

    /**
     * 获取ParameterizedType List<T> -> T TypeVariable 两种混合起来了
     */
    private List<T> tList;
    private static void printTypeVariable(String fieldName, TypeVariable typeVariable) {
        for (Type type : typeVariable.getBounds()) {
            log.info(fieldName + ": TypeVariable getBounds " + type);
        }
        log.info("定义Class getGenericDeclaration: " + typeVariable.getGenericDeclaration());
        log.info("getName: " + typeVariable.getName());
    }

    public static void main(String[] args) {
        Field f;
            Field[] fields = TypeVariableTest.class.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                f = fields[i];
                if (f.getName().equals("log")) {
                    continue;
                }
                log.info("begin************当前field:" + f.getName() + " ***********");
                if (f.getGenericType() instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) f.getGenericType();
                    for (Type type : parameterizedType.getActualTypeArguments()) {
                        log.info(f.getName() + ": 获取ParameterizedType:" + type);
                        if (type instanceof TypeVariable) {
                            printTypeVariable(f.getName(), (TypeVariable) type);
                        }
                    }
                } else {
                    log.info("type :" + f.getGenericType());
                }
                log.info("************************end**************************");
            }
    }
}
