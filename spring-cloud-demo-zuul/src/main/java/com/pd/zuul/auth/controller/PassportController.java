package com.pd.zuul.auth.controller;/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 10:58
 */

import com.google.common.base.Preconditions;
import com.pd.zuul.auth.model.LoginVo;
import com.pd.zuul.auth.model.User;
import com.pd.zuul.auth.service.UserService;
import com.pd.zuul.auth.token.PhoneSmsToken;
import com.pd.zuul.auth.token.PrincipalPasswordToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.pd.zuul.auth.util.Number;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 10:58
 */
@RestController
public class PassportController implements ApplicationContextAware {

    private UserService userService;
    private ApplicationContext applicationContext;
    private static final int COOKIE_EXPIRE_TIME = 24*60*60;
    @Autowired
    public void setUserService(UserService service){
        this.userService = service;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping("/login")
    public User login(@Validated LoginVo info, HttpServletRequest request, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = null;
        switch (info.getType()){
            case LOGIN_NORMAL:
                Preconditions.checkNotNull(info.getUserName(), "Preconditions:" + "username");
                token = new PrincipalPasswordToken(info.getUserName(), info.getPassword());
                break;
            /*case LOGIN_SMS:
                token = new PhoneSmsToken(info.getUserName(), info.getPassword());
                break;*/
            /*case LOGIN_LDAP:
                token = new LdapToken(info.getUserName(), info.getPassword());
                break;*/
            default:
                throw new IllegalStateException("Unexpected value: " + info.getType());
        }
        subject.login(token);
        User user = userService.findUserByUserName(info.getUserName());
        String accessToken = userService.login(user,request);
        setAccessToken(response,accessToken,Number.getHexStr(user.getUid()));
        return user;
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response){

    }

    private void setAccessToken(HttpServletResponse response, String accessToken, String uid) {
        Cookie tokenCookie = new Cookie("ACCESS_TOKEN", accessToken);
        tokenCookie.setMaxAge(COOKIE_EXPIRE_TIME);
        tokenCookie.setPath("/");
        tokenCookie.setHttpOnly(true);
        response.addCookie(tokenCookie);
        Cookie uidCookie = new Cookie("ACCESS_UID", uid);
        uidCookie.setMaxAge(COOKIE_EXPIRE_TIME);
        uidCookie.setPath("/");
        response.addCookie(uidCookie);
    }
}
