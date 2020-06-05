package com.pd.redis.sort;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/5 09:41
 */
@Service
@Slf4j
public class SortTestService {
    private ZSetOperations<String,Object> zSetOperations;
    @Autowired
    public void setZSetOperations(ZSetOperations<String, Object> zSetOperations) {
        this.zSetOperations = zSetOperations;
    }
    private static final Set<Player> PLAYERS = new HashSet<>();
    static {
        PLAYERS.add(new Player("今天只做一件事",12345));
        PLAYERS.add(new Player("明天会更好",33511));
        PLAYERS.add(new Player("来不及爱你",37652));
        PLAYERS.add(new Player("樱桃小丸子",94561));
        PLAYERS.add(new Player("你上了我的心",61234));
        PLAYERS.add(new Player("这辈子值",41231));
    }

    private static final String SORT_TEST = "sort_test_key";

    public void init(){
        for(Player p : PLAYERS){
            zSetOperations.add(SORT_TEST,p,p.getScore());
        }
    }

    public Set<Object> getTop(int num){
        return zSetOperations.reverseRange(SORT_TEST,0,num-1);
    }

    public Set<Object> range(){
        Set<Object> objects = zSetOperations.range(SORT_TEST,1,4);
        for (Object o : objects){
            System.out.println(o.toString());
        }
        return objects;
    }

    public void rangeWithScore(){
        Set<ZSetOperations.TypedTuple<Object>> res = zSetOperations.rangeWithScores(SORT_TEST,0,5);
        for(ZSetOperations.TypedTuple<Object> tuple: res){
            System.out.println(tuple.getValue().toString());
        }
    }
}
