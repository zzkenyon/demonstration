package com.pd.validator.bean;

import com.pd.validator.annotation.NumberLength;
import lombok.Data;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-11-30 20:11
 */
@Getter
public class Order {
    @NumberLength("2,4,6,8")
    private String orderId;
    /*private Date createTime;
    private String customName;
    private Double account;
    */
    @NotNull
    @Valid
    private List<OrderItem> itemList;

    public Order setOrderId(String id){
        this.orderId = id;
        return this;
    }

    public Order setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
        return this;
    }

    @Override
    public String toString(){
        return "订单号为" + orderId;
    }
}
