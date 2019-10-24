package com.pd.properties.controller;

import com.pd.properties.properties.MailProperties;
import com.pd.properties.properties.MessageProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PropertyController {
    @Resource
    private MailProperties mail;
    @Resource
    private MessageProperties message;

    @GetMapping("/mail")
    public MailProperties mailProperties(){
        return mail;
    }
    @GetMapping("/message")
    public MessageProperties messageProperties(){
        return message;
    }
}
