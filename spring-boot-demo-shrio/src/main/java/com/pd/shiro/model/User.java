package com.pd.shiro.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Jay_Liu
 * @Description:
 * @Date: Created in 21:01 2018/3/24 0024
 * @Modified By:
 */
public class User {

    private Integer uid;

    private String username;

    private String password;

    private Set<Role> roles = new HashSet<>();

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
