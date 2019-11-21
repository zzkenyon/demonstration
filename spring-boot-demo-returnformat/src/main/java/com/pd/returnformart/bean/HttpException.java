package com.pd.returnformart.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author zhaozhengkang@cetiti.com
 * @description
 * @date 2019/11/20 14:54
 */
@AllArgsConstructor
@Getter
public class HttpException extends RuntimeException {

    private int code;
    private HttpStatus httpStatus;
    private String msg;
}
