package com.pd.redis.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解的方法不能进行内部调用，否则注解不生效，原理同@Transactional内部调用事务不生效
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-20 14:21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {
    String value() default "";
    long timeOut() default 5000;
}
