package com.pd.shiro.model;

/**
 * @Author: Jay_Liu
 * @Description:
 * @Date: Created in 21:02 2018/3/24 0024
 * @Modified By:
 */
public class Permission {

    private Integer pid;

    private  String name;

    private  String url;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
