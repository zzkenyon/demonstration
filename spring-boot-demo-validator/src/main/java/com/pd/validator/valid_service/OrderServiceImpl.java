package com.pd.validator.valid_service;

import com.pd.validator.annotation.NumberLength;
import com.pd.validator.bean.Order;
import com.pd.validator.bean.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;

import javax.validation.GroupSequence;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-12-1 20:15
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Override
    public Object hello(@NotNull @Min(10) Integer id, @NotNull String name) {
        return null;
    }

    @Override
    public Order queryById(String id) throws Exception {
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem("水笔",10.00));
        items.add(new OrderItem("笔记本",null));
        Order order = new Order().setOrderId(id).setItemList(items);
        System.out.println(order.toString());
        return order;
    }

    @Override
    public String saveOrder(Order order) throws Exception {
        System.out.println(order.toString());
        return order.toString();
    }
}
