package com.pd.zuul.auth.config;

import com.pd.zuul.auth.realm.AccessTokenRealm;
import com.pd.zuul.auth.realm.SqlDatabaseRealm;
import com.pd.zuul.common.HttpException;
import lombok.SneakyThrows;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.realm.Realm;

/**
 * FirstSuccessfulStrategy
 *
 * @author liuzhaoqi@cetiti.com
 * @since 0.1.0
 */
public class FirstSuccessfulThrowableStrategy extends FirstSuccessfulStrategy {

    @SneakyThrows
    @Override
    public AuthenticationInfo afterAttempt(Realm realm,
                                           AuthenticationToken token,
                                           AuthenticationInfo singleRealmInfo,
                                           AuthenticationInfo aggregateInfo,
                                           Throwable throwable) {

        if(throwable instanceof HttpException && realm instanceof SqlDatabaseRealm){
            throw throwable;
        }
        if(throwable instanceof AuthenticationException && realm instanceof AccessTokenRealm){
            throw throwable;
        }
        return super.afterAttempt(realm, token, singleRealmInfo, aggregateInfo, throwable);
    }
}