package com.pd.starter.services;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2019-11-25 22:26
 */
public class DemoService {
    private String sayWhat;
    private String toWho;
    public DemoService(String sayWhat, String toWho){
        this.sayWhat = sayWhat;
        this.toWho = toWho;
    }
    public String say(){
        return this.sayWhat + "!  " + toWho;
    }
}
