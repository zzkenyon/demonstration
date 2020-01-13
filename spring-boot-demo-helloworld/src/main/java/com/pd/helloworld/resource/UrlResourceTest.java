package com.pd.helloworld.resource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/10 12:21
 */
public class UrlResourceTest {
    public static void main(String[] args) throws IOException {
        Resource resource = new UrlResource("file:/E:/Project-GIT/demonstration/spring-boot-demo-helloworld/target/classes/config.properties");
        InputStream is = resource.getInputStream();
        System.out.println(resource.getURL());
        System.out.println(resource.getURI());
        System.out.println(resource.getDescription());
        System.out.println(resource.contentLength());

    }
}