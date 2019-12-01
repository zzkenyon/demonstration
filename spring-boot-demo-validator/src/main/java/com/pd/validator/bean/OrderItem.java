package com.pd.validator.bean;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-11-30 21:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @NotEmpty
    private String goodName;
    @NotNull
    private Double price;
}
