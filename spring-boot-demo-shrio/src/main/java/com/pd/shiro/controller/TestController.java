package com.pd.shiro.controller;

import com.pd.shiro.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 * @Author: Jay_Liu
 * @Description:
 * @Date: Created in 20:18 2018/3/26 0026
 * @Modified By:
 */
@Controller
public class TestController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        //用户不为空，则手动登出
        if(subject !=null){
            subject.logout();
        }
        return "login";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin(){
        return "admin success";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(){
        return "edit success";
    }

    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session){
        System.out.println("username:"+username+",password:"+password);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //主体
        Subject subject = SecurityUtils.getSubject();
        //认证逻辑可能出现异常
        try{
            //主体进行登陆
            subject.login(token);
            //获取登陆用户
            User user = (User) subject.getPrincipal();
            //写入Session
            session.setAttribute("user",user);
            return "index";
        }catch (Exception e){
            return "login";
        }


    }
}
