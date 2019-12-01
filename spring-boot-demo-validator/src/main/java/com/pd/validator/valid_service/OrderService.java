package com.pd.validator.valid_service;

import com.pd.validator.annotation.NumberLength;
import com.pd.validator.bean.Order;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Validated(Default.class)
public interface OrderService {
    Object hello(@NotNull @Min(10) Integer id, @NotNull String name);

    Order queryById(@NumberLength("4,6,8,10,12") @Length(max=10) String id)throws Exception;

    String saveOrder(@Valid Order order)throws Exception;
}
