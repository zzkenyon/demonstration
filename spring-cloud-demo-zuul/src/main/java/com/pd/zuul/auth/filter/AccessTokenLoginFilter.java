package com.pd.zuul.auth.filter;
/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 10:37
 */

import com.alibaba.fastjson.JSON;
import com.pd.zuul.auth.PassportErrorCode;
import com.pd.zuul.auth.token.AccessToken;
import com.pd.zuul.common.constant.CommonErrorCode;
import com.pd.zuul.common.bean.RestResponseEntity;
import org.apache.http.HttpStatus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 10:37
 */
public class AccessTokenLoginFilter extends AuthenticatingFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return executeLogin(request, response);
    }
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response){
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies = req.getCookies();
        if(cookies == null){
            return null;
        }
        String accessToken = "";
        for(Cookie cookie : cookies){
            if(!cookie.getName().equals("ACCESS_TOKEN")){
                continue;
            }
            return new AccessToken(cookie.getValue());
        }
        return null;
    }
    @Override
    protected boolean  executeLogin(ServletRequest request, ServletResponse response) {
        AuthenticationToken token = createToken(request,response);
        if(token == null){
            return onLoginFailure(null,null,request,response);
        }
        try{
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            return true;
        }catch (AuthenticationException e ){
            return onLoginFailure(token,e,request,response);
        }

    }
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        String responseStr;
        if(token==null){
            responseStr = JSON.toJSONString(RestResponseEntity.error(PassportErrorCode.NOT_LOGIN.getHttpException()).getBody());
        }else {
            switch (e.getMessage()){
                case "TOKEN_INVALID":
                    responseStr = JSON.toJSONString(RestResponseEntity.error(PassportErrorCode.TOKEN_EXPIRED.getHttpException()).getBody());
                    break;
                case "TOKEN_EXPIRED":
                    responseStr = JSON.toJSONString(RestResponseEntity.error(PassportErrorCode.TOKEN_INVALID.getHttpException()).getBody());
                    break;
                default:
                    responseStr = JSON.toJSONString(RestResponseEntity.error(CommonErrorCode.COMMON_FAILED.getHttpException()).getBody());
            }

        }
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.reset();
        httpServletResponse.setStatus(HttpStatus.SC_UNAUTHORIZED);
        httpServletResponse.setHeader("Content-type","application/json;charset=UTF-8");
        try {
            httpServletResponse.getWriter().write(responseStr);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }


}
