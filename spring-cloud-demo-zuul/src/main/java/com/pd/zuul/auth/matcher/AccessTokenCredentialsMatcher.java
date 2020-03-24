package com.pd.zuul.auth.matcher;/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 14:37
 */


import com.pd.zuul.auth.service.TokenService;
import com.pd.zuul.auth.token.AccessToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 14:37
 */
public class AccessTokenCredentialsMatcher extends SimpleCredentialsMatcher {

    public static final String ERROR_TOKEN_INVALID = "TOKEN_INVALID";
    public static final String ERROR_TOKEN_EXPIRED = "TOKEN_EXPIRED";
    @Autowired
    private TokenService tokenService;
    private static final int TOKEN_EXPIRE_TIME = 24*60*60;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        // TODO: 应校验已经加入Redis黑名单的token(用于用户临时注销)
        // TODO: 判断UID是否在禁止登录黑名单中(可以通过缓存技术缓存下来，securitymanager中增加blacklistmanager数据库中的黑名单用户表)
        // TODO: 判断用户是否出现了角色变更，角色变更重新生成token, 并标记旧token无效
        AccessToken accessToken = (AccessToken) token;
        String strAccessToken = (String) token.getCredentials();
        int tokenVerify = tokenService.verify(strAccessToken);
        if (tokenVerify == 1) {
            // token 无效  拦截
            throw new AuthenticationException(ERROR_TOKEN_INVALID);
        }
        if (tokenVerify == 2) {
            // token 过期  拦截
            throw new AuthenticationException(ERROR_TOKEN_EXPIRED);
        }
        return true;
    }

}
