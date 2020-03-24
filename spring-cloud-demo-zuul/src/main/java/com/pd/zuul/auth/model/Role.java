package com.pd.zuul.auth.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Jay_Liu
 * @Description:
 * @Date: Created in 21:02 2018/3/24 0024
 * @Modified By:
 */
@Data
public class Role {

    private Integer rid;

    private String roleName;

    private Set<Permission> permissions = new HashSet<>();

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", roleName='" + roleName + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
