package com.pd.helloworld.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/12/3 13:52
 */
@Component
public class Cooker implements ApplicationListener<HungryEvent> {
    @Override
    public void onApplicationEvent(HungryEvent event) {
        if(event.getSource() instanceof Person){
            System.out.println("厨师接收到事件");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("完成食物，请用餐");
        }
    }
}
