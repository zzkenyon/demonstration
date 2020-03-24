package com.pd.zuul.auth.realm;

import com.pd.zuul.auth.model.Permission;
import com.pd.zuul.auth.model.Role;
import com.pd.zuul.auth.model.User;
import com.pd.zuul.auth.service.UserService;
import com.pd.zuul.auth.token.PrincipalPasswordToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: Jay_Liu
 * @Description:
 * @Date: Created in 16:34 2018/3/26 0026
 * @Modified By:
 */

public class SqlDatabaseRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof PrincipalPasswordToken;
    }

    /**
     * 认证登陆成功后把User对象放入session中,重载该方法的主要目的是根据登录认证信息（用户名）获取user对象，放入session中由shiro完成
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        PrincipalPasswordToken principalPasswordToken = (PrincipalPasswordToken) authenticationToken;
        //从转换后的token中获取用户名的信息
        String username = (String) principalPasswordToken.getPrincipal();
        //DB获取用户的密码
        User user = userService.findUserByUserName(username);
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }

    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //session中获取用户
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        //权限集合
        List<String> permissionList = new ArrayList<>();
        //角色集合
        List<String> roleNameList = new ArrayList<>();
        Set<Role> roleSet =  user.getRoles();
        if(!roleSet.isEmpty()){
            for(Role role : roleSet){
                roleNameList.add(role.getRoleName());
                Set<Permission> permissionSet = role.getPermissions();
                if(!permissionSet.isEmpty()){
                    for(Permission permission : permissionSet){
                        //放入权限集合
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleNameList);
        return info;
    }
}
