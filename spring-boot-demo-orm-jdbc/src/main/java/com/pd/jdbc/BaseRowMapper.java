package com.pd.jdbc;

import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Map;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-05-18 13:46
 */
@NoArgsConstructor
public class BaseRowMapper<T> implements RowMapper<T> {
    private Class<?> targetClazz;
    private Map<String, Field> fieldMap;


    public BaseRowMapper(Class<?> clazz){
        this.targetClazz = clazz;
        Field[] fields = clazz.getDeclaredFields();
        for(Field f : fields){
            fieldMap.put(f.getName(),f);
        }
    }

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            T res = (T)targetClazz.getConstructor().newInstance();
            for(Map.Entry<String,Field> entry : fieldMap.entrySet()){
                Field field = entry.getValue();
                field.setAccessible(true);
                Class<?> fieldClazz = field.getType();
                if(fieldClazz == String.class){
                    field.set(res,rs.getString(camelTo_(entry.getKey())));
                }else if(fieldClazz == Integer.class || fieldClazz == int.class){
                    field.set(res,rs.getInt(camelTo_(entry.getKey())));
                }else if(fieldClazz == Double.class || fieldClazz == double.class){
                    field.set(res,rs.getDouble(camelTo_(entry.getKey())));
                }else if(fieldClazz == Float.class || fieldClazz == float.class){
                    field.set(res,rs.getFloat(camelTo_(entry.getKey())));
                }else if(fieldClazz == Short.class || fieldClazz == short.class){
                    field.set(res,rs.getShort(camelTo_(entry.getKey())));
                }else if(fieldClazz == Boolean.class || fieldClazz == boolean.class){
                    field.set(res,rs.getBoolean(camelTo_(entry.getKey())));
                }else if(fieldClazz == Long.class || fieldClazz == long.class){
                    field.set(res,rs.getLong(camelTo_(entry.getKey())));
                }else if(fieldClazz == Date.class){
                    field.set(res,rs.getDate(camelTo_(entry.getKey())));
                }else if (fieldClazz == Timestamp.class){
                    field.set(res,rs.getTimestamp(camelTo_(entry.getKey())));
                }
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String camelTo_(String fieldName){
        StringBuilder sb = new StringBuilder(fieldName);
        char[] strArray = fieldName.toCharArray();
        for (int i = 0; i < strArray.length; i++) {
            if(strArray[i] >= 65 && strArray[i] < 91){
                sb.replace(i,i+1,"_"+(char)(strArray[i]+32));
            }
        }
        return sb.toString();
    }

}
