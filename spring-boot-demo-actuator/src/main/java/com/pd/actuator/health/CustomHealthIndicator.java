package com.pd.actuator.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @author zhaozhengkang@cetiti.com
 * @description 自定义health测试
 * @date 2019/11/25 14:33
 */
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        /**
         * 使用 builder 添加健康状态的详细内容
         * 如果方法抛出异常，status 将会报 DOWN,并在detail中报告异常信息
         */
        // Use the builder to build the health status details that should be reported.
        // If you throw an exception, the status will be DOWN with the exception message.
        if(1==2){
            throw new RuntimeException("这是一个测试异常");
        }else {
            builder.up()
                    .withDetail("app", "Alive and Kicking")
                    .withDetail("error", "Nothing! I'm good.");
        }
    }
}
