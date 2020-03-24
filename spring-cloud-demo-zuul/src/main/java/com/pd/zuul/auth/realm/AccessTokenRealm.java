package com.pd.zuul.auth.realm;/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 16:24
 */

import com.pd.zuul.auth.model.Permission;
import com.pd.zuul.auth.model.Role;
import com.pd.zuul.auth.model.User;
import com.pd.zuul.auth.service.TokenService;
import com.pd.zuul.auth.service.UserService;
import com.pd.zuul.auth.token.AccessToken;
import org.apache.http.util.NetUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 16:24
 */
public class AccessTokenRealm extends AuthorizingRealm {

    private TokenService tokenService;
    private UserService userService;
    @Autowired
    public void setTokenService(TokenService tokenService){
        this.tokenService = tokenService;
    }
    @Autowired
    public void setUserService(UserService service){
        this.userService = service;
    }
    /**
     *
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token!=null && token instanceof AccessToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //session中获取用户
        User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
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

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getCredentials();
        User user = userService.findUserByUid(tokenService.getUserId(accessToken));
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
