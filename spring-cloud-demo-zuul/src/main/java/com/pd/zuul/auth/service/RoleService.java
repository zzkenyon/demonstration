package com.pd.zuul.auth.service;


import com.pd.zuul.auth.model.Role;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/3/12 9:08
 */
public interface RoleService {
    /**
     * 创建新角色
     * @param role
     * @return
     */
     Role createRole(Role role);

    /**
     * 删除已有角色
     * @param roleId
     */
     void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
     void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
     void removePermissions(Long roleId, Long... permissionIds);
}