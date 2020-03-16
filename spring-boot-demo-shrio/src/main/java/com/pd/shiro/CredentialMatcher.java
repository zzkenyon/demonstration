package com.pd.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @Author: Jay_Liu
 * @Description: 自定义密码校验规则
 * @Date: Created in 19:05 2018/3/26 0026
 * @Modified By:
 */
public class CredentialMatcher extends SimpleCredentialsMatcher{

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //获取session中的密码
        String password = new String(usernamePasswordToken.getPassword());
        //从Realm中传递过来的数据库中的密码
        String dbPassword = (String) info.getCredentials();
        return this.equals(password,dbPassword);
    }
}
