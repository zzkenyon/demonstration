package com.pd.zuul.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.pd.zuul.common.constant.CommonErrorCode.COMMON_SUCCESS;

/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 16:39
 */
@Data
@AllArgsConstructor
public class ResponseMeta {

    public static final ResponseMeta SUCCESS_META = new ResponseMeta(
            COMMON_SUCCESS.getHttpException().getCode(),
            COMMON_SUCCESS.getHttpException().getMsg()
    );

    public int code;
    public String msg;

}
