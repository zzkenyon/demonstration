package com.pd.reids;

import com.pd.reids.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ValueOperations;


/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-01-03 20:30
 */
@SpringBootTest
class RedisTest {
    @Autowired
    private ValueOperations<String,Object> valueOperations;
    @Autowired
    private HashOperations<String,String,Object> hashOperations;

    @Test
    void setStringTest(){
        valueOperations.set("key_str","string test");
    }
    @Test
    void getStringTest(){
        print(valueOperations.get("key_str").toString());
    }

    @Test
    void setPOJOTest(){
        valueOperations.set("key_user",new User(1L,"zzk"));
    }

    @Test
    void getPOJPTest(){
        User user = (User)valueOperations.get("key_user");
        print(user.toString());
    }

    @Test
    void setHashTest(){
        hashOperations.put("key_hash","tec",new User(2L,"wangshun"));
    }
    @Test
    void getHashTest(){
        User user = (User)hashOperations.get("key_hash","tec");
        print(user.toString());
    }


    private void print(String s){
        System.out.println(s);
    }
}
