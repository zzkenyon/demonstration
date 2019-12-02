package com.pd.validator.bean;

import com.pd.validator.annotation.NumberLength;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
    @NumberLength("4,6")
    private String goodName;
    @NotNull
    private Double price;

    @Override
    public String toString(){
        return "goodName is " + this.getGoodName();
    }
}
