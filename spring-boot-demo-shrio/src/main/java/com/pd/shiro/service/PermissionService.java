package com.pd.shiro.service;

import com.pd.shiro.model.Permission;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/3/12 9:10
 */
public interface PermissionService {
    /**
     * 创建新权限
     * @param permission
     * @return
     */
     Permission createPermission(Permission permission);

    /**
     * 删除已有权限
     * @param permissionId
     */
     void deletePermission(Long permissionId);
}