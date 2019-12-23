package com.pd.helloworld.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author zhaozhengkang
 * @description
 * @date 2019/12/9 10:17
 */
@Slf4j
@Service
public class ResourceTest {

    private static void dumpStream(Resource resource) {
        InputStream is = null;
        try {
//1.获取文件资源
            is = resource.getInputStream();
//2.读取资源
            byte[] descBytes = new byte[is.available()];
            is.read(descBytes);
            System.out.println(new String(descBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
//3.关闭资源
                is.close();
            } catch (IOException e) {
            }
        }
    }
    public static void main(String[] args) {
        Resource resource = new ByteArrayResource("Hello World!".getBytes());
        if(resource.exists()) {
            dumpStream(resource);
        }
    }
}
