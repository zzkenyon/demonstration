package com.pd.shiro.accesstoken;

import com.pd.shiro.accesstoken.Algorithm;

import javax.servlet.http.Cookie;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/3 16:32
 */
public interface AccessTokenService {
    /**
     * 验证token是否有效
     * @param token Token
     * @return 0 有效   1无效   2过期
     */
    int verifyToken(String token);


    /**
     * 根据用户id生成 token
     * @return Token
     */
    String generateToken();
    String generateToken(Algorithm alg);

    /**
     *  退出登录
     * @param token Token
     */
    boolean logout(String token);

    /**
     *  从cookie中获取 IB_TOKEN
     */
    String getTokenFromCookie(Cookie[] cookie);

    /**
     * 通过token获取用户名
     * @param token Token
     * @return 用户名
     */
    String getUsername(String token);

    /**
     * 通过Token换取用户ID
     * @param token Token
     * @return 用户ID
     */
    int getUserId(String token);
}
