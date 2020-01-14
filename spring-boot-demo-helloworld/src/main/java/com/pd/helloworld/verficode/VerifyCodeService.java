package com.pd.helloworld.verficode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaozhengkang
 */
public interface VerifyCodeService {
    /**
     * 生成base64编码的字符串形式的图片
     * @return
     */
    String createPicVerificationCode(String param,HttpServletRequest request);

}
