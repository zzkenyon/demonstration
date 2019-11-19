package com.pd.swagger.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhaozhengkang@cetiti.com
 * @description
 * @date 2019/11/19 14:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 5057954049311281252L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 工作岗位
     */
    private String job;
}

