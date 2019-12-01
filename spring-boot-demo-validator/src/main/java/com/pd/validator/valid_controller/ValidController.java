package com.pd.validator.valid_controller;

import com.pd.validator.annotation.NumberLength;
import com.pd.validator.bean.Order;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-12-1 19:59
 */
@RestController
@Validated //用于平铺参数校验
public class ValidController {
    /**
     * 平铺参数/返回值 校验
     * @param name
     */
    @GetMapping("/get_para_valid")
    public @Length(min=4) String getParaValidated(@RequestParam @NumberLength("2,4,6") String name){
        return name;
    }

    /**
     * JavaBean 入参校验
     * {"orderId": "123","itemList":[{"goodName": "水笔","price": 10.00},{"goodName": "水笔","price": null}] }
     * @param order
     * @param result
     */
    @PostMapping(value = "/valid")
    public void getById(@RequestBody @Valid Order order, BindingResult result){//此处使用@Validated效果一样
        if(result .hasErrors()){
            for (ObjectError error :result.getAllErrors()) {
                throw new RuntimeException(error.getDefaultMessage());
            }
        }
    }
}
