package com.pd.helloworld.generic;

import lombok.Data;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/12/5 13:34
 */
public class ResolvableTypeTest {
    private List<String> strList;
    interface Fruit{}
    static class SweetFruit implements Fruit{}
    static class Apple<T extends Fruit>{
        T value;
    }
    @Data
    static class ShandiApple extends Apple<SweetFruit>{

    }
    public static void main(String[] args) throws Exception{
        Field f = ResolvableTypeTest.class.getDeclaredField("strList");
        ResolvableType type = ResolvableType.forField(f);
        ResolvableType superT = type.getSuperType();
        ResolvableType[] interfaces = superT.getInterfaces();
        ResolvableType generic = type.getGeneric();
        System.out.println(Apple.class.getName());
    }
}
