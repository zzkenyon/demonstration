package com.pd.helloworld.generic;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/12/5 14:24
 */
public class Generic {
    private static class DemoClass<T>{
        List<T> ts;
        T t;

        class SubClass<S extends List<String>>{
            S s;
        }
    }

    private static class GenericMethod{
        <S extends NullPointerException> void print(S s){
        }
    }


    public static void main(String[] args) throws Exception{

    }
    public static void testMethodTypeVarible()throws Exception{
        TypeVariable<?> [] variables = GenericMethod.class.getDeclaredMethod("print", NullPointerException.class).getTypeParameters();
        System.out.println("类型变量的边界是： " + variables[0].getBounds()[0].getTypeName());
        System.out.println("类型变量的名称是： " + variables[0].getName());
        System.out.println("该类型变量声明在：" + variables[0].getGenericDeclaration());
    }
    public static void testClassTypeVarible()throws Exception{
        TypeVariable<?>[] variables  = DemoClass.SubClass.class.getTypeParameters();
        System.out.println("TypeVariable Bound is " + variables[0].getBounds()[0].getTypeName());
        System.out.println("TypeVariable Name is " + variables[0].getName());
        System.out.println("TypeVariable 声明类 is" + variables[0].getGenericDeclaration());
    }
    public static void testDemoClass() throws Exception{
        Field field = DemoClass.class.getDeclaredField("ts");
        Type type = field.getType(); System.out.println(type);
        Type genericType = field.getGenericType();System.out.println(genericType);
        //转型成参数化类型
        ParameterizedType parameterizedType = (ParameterizedType)genericType;System.out.println(parameterizedType);
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];System.out.println(actualTypeArgument);
        Type rawType  = parameterizedType.getRawType();System.out.println(rawType);
    }
}
