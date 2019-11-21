package com.pd.returnformart.bean;

import lombok.AllArgsConstructor;
import lombok.Setter;

import static com.pd.returnformart.bean.CommonException.COMMON_SUCCESS;

/**
 * @author zhaozhengkang@cetiti.com
 * @description
 * @date 2019/11/20 14:53
 */
@Setter
@AllArgsConstructor
public class ResponseMeta {

    public static final ResponseMeta SUCCESS_META = new ResponseMeta(
            COMMON_SUCCESS.getHttpException().getCode(),
            COMMON_SUCCESS.getHttpException().getMsg()
    );

    public int code;
    public String msg;

}
