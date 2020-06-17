package com.pd.shiro.controller;/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/16 15:36
 */

import com.pd.shiro.accesstoken.AccessTokenService;
import com.pd.shiro.accesstoken.Number;
import com.pd.shiro.model.User;
import com.pd.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/16 15:36
 */
@RestController
public class PassportController {
    private static final int COOKIE_EXPIRE_TIME = 24*60*60;
    @Autowired
    private UserService userService;
    @Resource
    private AccessTokenService accessTokenService;


    @PostMapping("/login")
    public User login(@RequestBody LoginVo loginVo, HttpServletResponse response){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getUserName(),loginVo.getPassword());
        subject.login(token);
        User user = userService.findUserByUserName(loginVo.getUserName());
        String accessToken = accessTokenService.generateToken(String.valueOf(user.getUid()),1000);
        setAuthToken(response,accessToken, Number.getHexStr(user.getUid()));
        return user;
    }

    private void setAuthToken(HttpServletResponse response, String token, String uid) {
        Cookie tokenCookie = new Cookie("IB_TOKEN", token);
        tokenCookie.setMaxAge(COOKIE_EXPIRE_TIME);
        tokenCookie.setPath("/");
        tokenCookie.setHttpOnly(true);
        response.addCookie(tokenCookie);
        Cookie uidCookie = new Cookie("IB_UID", uid);
        uidCookie.setMaxAge(COOKIE_EXPIRE_TIME);
        uidCookie.setPath("/");
        response.addCookie(uidCookie);
    }
}
