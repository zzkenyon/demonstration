package com.pd.provider.common.bean;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 16:40
 */

@Setter
@AllArgsConstructor
public class RestResponseBody<D> {

    public ResponseMeta meta;
    public D data;

}
