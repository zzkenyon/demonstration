package com.pd.mongodb.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import java.util.List;
import java.util.Map;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-01-14 15:49
 */
public interface MongoService<T> {
    /**
     * 插入
     * @param info
     * @param collectionName
     */
    void insert(T info, String collectionName);

    /**
     * 批量插入
     * @param infos
     * @param collectionName
     */
    void insertMulti(List<T> infos, String collectionName);

    /**
     *
     * @param collectName
     * @param clazz
     * @return
     */
    List<T> selectList(String collectName, Class<T> clazz);
    /**
     * 查询 select * from table
     * @param collectName
     * @param clazz
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<T> selectList(String collectName, Class<T> clazz, Integer currentPage, Integer pageSize);

    /**
     * select * from table where conditions
     * @param collectionName
     * @param conditions
     * @param clazz
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<T> findByCondition(String collectionName,Map<String, Object> conditions, Class<T> clazz, Integer currentPage, Integer pageSize);


    UpdateResult update(String conditionName, Map<String,Object> conditions, T info);

    DeleteResult delete(String collectionName, Map<String,Object> conditions);
}
