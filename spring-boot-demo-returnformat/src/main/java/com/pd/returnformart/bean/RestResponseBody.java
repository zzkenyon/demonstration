package com.pd.returnformart.bean;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * @author zhaozhengkang@cetiti.com
 * @description
 * @date 2019/11/20 14:52
 */
@Setter
@AllArgsConstructor
public class RestResponseBody<D> {

    public ResponseMeta meta;
    public D data;

}
