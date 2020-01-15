package com.pd.mongodb.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-01-14 15:48
 */
@Service
@Slf4j
public class MongoServiceImpl<T> implements MongoService<T>{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(T info, String collectionName) {

        mongoTemplate.insert(info,collectionName);
    }

    @Override
    public void insertMulti(List<T> infos, String collectionName) {
        mongoTemplate.insert(infos,collectionName);
    }

    @Override
    public List<T> selectList(String collectName, Class<T> clazz) {
        return selectList(collectName,clazz,null,null);
    }

    @Override
    public List<T> selectList(String collectName, Class<T> clazz, Integer currentPage, Integer pageSize) {
        //设置分页参数
        Query query = new Query();
        //设置分页信息
        if (!ObjectUtils.isEmpty(currentPage) && ObjectUtils.isEmpty(pageSize)) {
            query.limit(pageSize);
            query.skip(pageSize * (currentPage - 1));
        }
        return mongoTemplate.find(query, clazz, collectName);
    }
    @Override
    public List<T> findByCondition(String collectionName, Map<String, Object> conditions, Class<T> clazz, Integer currentPage, Integer pageSize) {
        if (ObjectUtils.isEmpty(conditions)) {
            return selectList(collectionName, clazz, currentPage, pageSize);
        } else {
            //设置分页参数
            Query query = new Query();
            query.limit(pageSize);
            query.skip(currentPage*(currentPage-1));
            // 往query中注入查询条件
            conditions.forEach((key, value) -> query.addCriteria(Criteria.where(key).is(value)));
            return mongoTemplate.find(query, clazz, collectionName);
        }
    }

    @Override
    public UpdateResult update(String collectionName, Map<String,Object> conditions, T info){
        Query query = new Query();
        conditions.forEach((key, value) -> query.addCriteria(Criteria.where(key).is(value)));
        Update update = new Update();
        String str = JSON.toJSONString(info);
        JSONObject jQuery = JSON.parseObject(str);
        jQuery.forEach(update::set);
        return mongoTemplate.updateMulti(query,update,info.getClass(),collectionName);
    }
    @Override
    public DeleteResult delete(String collectionName,Map<String,Object> conditions){
        Query query = new Query();
        conditions.forEach((key, value) -> query.addCriteria(Criteria.where(key).is(value)));
        return mongoTemplate.remove(query,collectionName);
    }
}
