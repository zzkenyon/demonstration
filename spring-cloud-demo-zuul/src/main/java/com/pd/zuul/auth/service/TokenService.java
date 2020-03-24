package com.pd.zuul.auth.service;

import javax.servlet.http.Cookie;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/3 16:32
 */
public interface TokenService {
    /**
     * 验证token是否有效
     * @param token Token
     * @return 0 有效   1无效   2过期
     */
    int verify(String token);

    /**
     * 根据 uid 生成 access token
     * @param id
     * @return
     */
    String generateAccessToken(String id);
    /**
     * 通过Token换取用户ID
     * @param token Token
     * @return 用户ID
     */
    int getUserId(String token);
}
