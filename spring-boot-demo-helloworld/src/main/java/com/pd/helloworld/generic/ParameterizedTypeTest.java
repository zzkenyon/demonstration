package com.pd.helloworld.generic;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/12/5 9:55
 */
@Slf4j
public class ParameterizedTypeTest {

    private Map<String,ParameterizedTypeTest> map;
    private  List<String> strList;
    private Class<?> aClass;
    public List aList;
    protected String str;
    private Integer i;

    public static void main(String[] args) {
        Field f =null;
        Field[] fields =  ParameterizedTypeTest.class.getDeclaredFields();
        for (int i = 0; i< fields.length;i++){
            f = fields[i];
            if(f.getName().equals("log")){
                continue;
            }
            log.info(f.getName()+": field.getName =" + f.getName());
            log.info(f.getName()+": field.getType = " + f.getType());
            log.info(f.getName()+": filed.getGenericType = " + f.getGenericType());
            log.info("--");
            if(f.getGenericType() instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType) f.getGenericType();
                for(Type type :parameterizedType.getActualTypeArguments()){
                    log.info(f.getName()+": 获取类型参数:"+ type);
                }
                if(parameterizedType.getOwnerType() !=null){
                    log.info(f.getName()+": getOwnerType:"+parameterizedType.getOwnerType());
                }
                Type t = parameterizedType.getRawType();
                if(t != null){
                    log.info(f.getName()+": getRawType:"+parameterizedType.getRawType());
                }
            }else{
                log.info(f.getName()+": 没有类型参数 ");
            }
            log.info("*******************************************************************************");
        }
    }
}
