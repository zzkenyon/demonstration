package com.pd.zuul.auth.model;

import lombok.Data;

/**
 * @Author: Jay_Liu
 * @Description:
 * @Date: Created in 21:02 2018/3/24 0024
 * @Modified By:
 */
@Data
public class Permission {

    private Integer pid;

    private  String name;

    private  String url;


    @Override
    public String toString() {
        return "Permission{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
