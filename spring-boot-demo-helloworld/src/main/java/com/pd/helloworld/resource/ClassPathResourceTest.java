package com.pd.helloworld.resource;

import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/10 11:13
 */
public class ClassPathResourceTest {
    public static void main(String[] args) throws IOException {
        ClassPathResource resource = new ClassPathResource("config.properties");
        InputStream is = resource.getInputStream();
        System.out.println(resource.getURL());
        System.out.println(resource.getURI());
        System.out.println(resource.getDescription());
        System.out.println(resource.getFile().length());
    }
}
