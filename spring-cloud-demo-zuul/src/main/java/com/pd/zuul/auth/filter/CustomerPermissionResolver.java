package com.pd.zuul.auth.filter;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/16 10:38
 */
public class CustomerPermissionResolver implements PermissionResolver {
    @Override
    public Permission resolvePermission(String permissionString) {
        return new MyPermissionResolver(permissionString );
    }
}
class MyPermissionResolver implements Permission{

    private final String permissionString;

    public MyPermissionResolver(String permissionString) {
        this.permissionString = permissionString;
    }

    @Override
    public boolean implies(Permission p) {
        if(!(p instanceof MyPermissionResolver))
        {
            return false;
        }
        return true;
    }
}
