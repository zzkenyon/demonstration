package com.pd.helloworld.verficode;

import lombok.Data;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-01-13 15:28
 */
@Data
public class VerifyCode {

    private String context;

    private String base64VerifyCode;

    public static VerifyCode getInstance(){
        return new VerifyCodeGenerator().generateVerifyCode();
    }

}
