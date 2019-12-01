package com.pd.validator.valid_service;

import com.pd.validator.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-12-1 20:20
 */
@RestController
public class ServiceValidController {
    @Autowired
    OrderService orderService;

    @GetMapping(value = "/hello")
    public Object hello(@RequestParam Integer id,@RequestParam String name){
        return orderService.hello(id, name);
    }
    @GetMapping(value = "/service_valid")
    public Order queryById(@RequestParam String id) throws Exception{
        return orderService.queryById(id);
    }

    @PostMapping(value = "/service_valid_bean")
    public String saveOrder(@RequestBody Order order) throws Exception{
        return orderService.saveOrder(order);
    }
}
