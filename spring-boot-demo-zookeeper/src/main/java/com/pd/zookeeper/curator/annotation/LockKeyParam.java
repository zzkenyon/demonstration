package com.pd.zookeeper.curator.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LockKeyParam {
    String[] fields() default {};
}
