package com.pd.helloworld.resource;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/13 9:43
 */
public class FileSystemResourceTest {
    public static void main(String[] args) throws Exception{
        Resource resource = new FileSystemResource(new File("C:\\Users\\zhaozhengkang\\Desktop\\sbt vm参数.txt"));
        System.out.println(resource.getFilename());
        System.out.println(resource.contentLength());
        System.out.println(resource.getDescription());
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
