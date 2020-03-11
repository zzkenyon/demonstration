package com.pd.shiro;

import com.pd.shiro.model.Permission;
import com.pd.shiro.model.Role;
import com.pd.shiro.model.User;
import com.pd.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: Jay_Liu
 * @Description:
 * @Date: Created in 16:34 2018/3/26 0026
 * @Modified By:
 */
public class AuthRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    //授权(取出Session中的User对象)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //session中获取用户
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        String username = user.getUsername();
        //权限集合
        List<String> permissionList = new ArrayList<>();
        //角色集合
        List<String> roleNameList = new ArrayList<>();
        Set<Role> roleSet =  user.getRoles();
        if(!roleSet.isEmpty()){
            for(Role role : roleSet){
                roleNameList.add(role.getRname());
                Set<Permission> permissionSet = role.getPermissons();
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

    //认证登陆(认证登陆成功后把User对应放入session中)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //从转换后的token中获取用户名的信息
        String username = usernamePasswordToken.getUsername();
        //DB获取用户的密码
        User user = userService.findByUsername(username);
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }
}
