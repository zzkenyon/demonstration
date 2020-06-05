package com.pd.kafka.config;

import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/5 17:27
 */
public class ObjectDeserializer implements Deserializer<Object> {
    @Override
    public Object deserialize(String topic, byte[] data) {
        Object readObject = null;
        try (ByteArrayInputStream in = new ByteArrayInputStream(data);
             ObjectInputStream inputStream = new ObjectInputStream(in)){
            readObject = inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readObject;
    }
}

