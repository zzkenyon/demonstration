package com.pd.provider.service;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/21 10:02
 */
@Service
public class TestSmartLifeCycle implements SmartLifecycle {
    @Override
    public void start() {
        System.out.println("start");
    }

    @Override
    public void stop() {
        System.out.println("stop");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
