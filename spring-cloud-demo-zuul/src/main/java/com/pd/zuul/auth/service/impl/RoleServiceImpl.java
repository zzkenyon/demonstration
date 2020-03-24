package com.pd.zuul.auth.service.impl;


import com.pd.zuul.auth.model.Role;
import com.pd.zuul.auth.service.RoleService;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/3/12 9:12
 */
public class RoleServiceImpl implements RoleService {
    @Override
    public Role createRole(Role role) {
        return null;
    }

    @Override
    public void deleteRole(Long roleId) {

    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {

    }

    @Override
    public void removePermissions(Long roleId, Long... permissionIds) {

    }
}
