package com.pd.zuul.auth.model;/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/17 18:08
 */

import lombok.Data;

/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/17 18:08
 */
@Data
public class LoginVo {
    private String userName;

    private String password;

    private LoginType type;
}
