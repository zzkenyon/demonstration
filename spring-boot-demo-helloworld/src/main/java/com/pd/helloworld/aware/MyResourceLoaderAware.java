package com.pd.helloworld.aware;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/13 10:38
 */
@Service
public class MyResourceLoaderAware implements ResourceLoaderAware {
    private ResourceLoader resourceLoader;
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
       this.resourceLoader = resourceLoader;
    }

    public void resourceLoaderTest(){
        Resource resource = resourceLoader.getResource("config.properties");
        try(BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
