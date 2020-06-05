package com.pd.kafka.config;

import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/5 17:16
 */
public class ObjectSerializer implements Serializer<Object> {
    @Override
    public byte[] serialize(String topic, Object data) {
        byte[] res = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos);){
            oos.writeObject(data);
            oos.flush();
            res = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
