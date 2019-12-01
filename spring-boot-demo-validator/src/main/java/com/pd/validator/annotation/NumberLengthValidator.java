/*
 * Copyright (c) 2019. CETITI
 */

package com.pd.validator.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @author zhaozhengkang@cetiti.com
 */
public class NumberLengthValidator implements ConstraintValidator<NumberLength,String> {

    private String params;
    @Override
    public void initialize(NumberLength constraintAnnotation) {
        this.params = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String[] ps = params.split(",");
        int[] intPs = new int[ps.length];
        for(int i = 0; i<ps.length; i++){
            intPs[i] = Integer.parseInt(ps[i]);
        }
        for(int j = 0;j< intPs.length;j++){
            if(value.length() == intPs[j]){
                return true;
            }else {
                continue;
            }
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("invalid number, length error!").
                addConstraintViolation();
        return false;
    }
}
