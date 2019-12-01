/*
 * Copyright (c) 2019. CETITI
 */

package com.pd.validator.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author zhaozhengkang@cetiti.com
 */
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumberLengthValidator.class)
@Documented
public @interface NumberLength {
    String message() default "default message";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String value();
}