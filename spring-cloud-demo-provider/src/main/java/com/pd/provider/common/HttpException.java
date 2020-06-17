package com.pd.provider.common;/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 16:41
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 16:41
 */
@AllArgsConstructor
@Data
public class HttpException extends RuntimeException {

    private int code;
    private HttpStatus httpStatus;
    private String msg;
}
