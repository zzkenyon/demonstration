package com.pd.redis.limiter;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimiter {
    /**
     * 每分钟访问次数
     */
    int perMinter() default 0;

    int perSec() default 0;

    int perHour() default 0;
}
