package com.pd.zuul.auth.model;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Jay_Liu
 * @Description:
 * @Date: Created in 21:01 2018/3/24 0024
 * @Modified By:
 */
@Data
public class User {

    private Integer uid;

    private String userName;

    private String password;

    private Set<Role> roles = new HashSet<>();

    private String lastLoginIp;

    private Date lastLoginTime;

    private Integer loginCount;
    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
