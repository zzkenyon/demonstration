package com.pd.provider.service;

import com.pd.provider.bean.User;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020-1-1 15:21
 */
public interface IUserService {
    /**
     * 获取用户
     *
     * @param id key值
     * @return 返回结果
     */
    User get(Long id);

    /**
     * 删除
     *
     * @param id key值
     */
    void delete(Long id);
}
