package com.pd.helloworld.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/12/3 13:49
 */

public class HungryEvent extends ApplicationEvent {
    public HungryEvent(Object source) {
        super(source);
    }
}
