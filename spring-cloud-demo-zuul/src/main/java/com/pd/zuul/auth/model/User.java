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

    private Long uid;
    private String userName;
    private String password;
    private String publicSalt;
    private Set<Role> roles = new HashSet<>();
    private Boolean locked = Boolean.FALSE;
    /**
     * 以下是业务数据
     */
    private String lastLoginIp;
    private Date lastLoginTime;
    private Integer loginCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (uid != null && uid.equals(user.uid)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return uid != null ? uid.hashCode() : 0;
    }
}
