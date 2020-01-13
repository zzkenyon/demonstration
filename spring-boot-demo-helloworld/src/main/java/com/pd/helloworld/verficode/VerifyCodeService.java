package com.pd.helloworld.verficode;

import java.awt.image.BufferedImage;

/**
 * @author zhaozhengkang
 */
public interface VerifyCodeService {
    /**
     * 获取验证码信息
     * @return
     */
    String getVerifyText();

    /**
     * 生成验证码图片
     * @return
     */
    BufferedImage generateVerifyCode();

}
