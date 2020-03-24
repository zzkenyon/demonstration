package com.pd.zuul.auth.token;/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 16:17
 */

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/3/23 16:17
 */
@Data
public class AccessToken implements AuthenticationToken {
    private String accessToken;

    public AccessToken(String value) {
        this.accessToken = value;
    }

    @Override
    public Object getPrincipal() {
        return getAccessToken();
    }

    @Override
    public Object getCredentials() {
        return getAccessToken();
    }
}
