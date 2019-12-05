package com.pd.helloworld.generic;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.*;
import java.util.List;
import java.util.Set;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/12/5 10:35
 */
@Slf4j
public class GenericArrayTypeTest <T> {
    public void testGenericArrayType(
            List<String>[] pTypeArray,
            T[] vTypeArray,
            String ste,
            String[] strings,
            List<String> list,
            Set<T> set,
            GenericArrayTypeTest[] test) {
    }

    public static void main(String[] args) {
        Method m ;
        Method[] methods = GenericArrayTypeTest.class.getDeclaredMethods();
        for(int i = 0; i < methods.length; i++){
            m = methods[i];
            if(m.getName().equals("main"))
                continue;
            Type[] types = m.getGenericParameterTypes();
            Type[] types1 = m.getParameterTypes();
            for(Type type: types){
                if(type instanceof ParameterizedType){
                    log.info(type.getTypeName() + " 是参数化类型 :"+type);
                    Type[] pType = ((ParameterizedType) type).getActualTypeArguments();
                    for(int j =0;j< pType.length;j++){
                        log.info(type.getTypeName() + " 的类型参数是 : " + pType[j]);
                    }
                }else if(type instanceof GenericArrayType){
                    log.info(type.getTypeName() + " 是泛型数组类型 :"+type);
                    Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
                    /**
                     * 获取泛型数组中元素的类型，要注意的是：无论从左向右有几个[]并列，
                     * 这个方法仅仅脱去最右边的[]之后剩下的内容就作为这个方法的返回值。
                     */
                    log.info(type.getTypeName() + "泛型数组成员类型是："+genericComponentType);
                }else if(type instanceof WildcardType){
                    log.info(type.getTypeName() + "是WildcardType 类型 :"+type);
                }else if(type instanceof TypeVariable){
                    log.info(type.getTypeName() + "是TypeVariable 类型 :"+type);
                }else {
                    log.info(type.getTypeName() + "是type 类型 :"+type);
                }
                log.info("********************************************************");
            }

        }
    }

}
