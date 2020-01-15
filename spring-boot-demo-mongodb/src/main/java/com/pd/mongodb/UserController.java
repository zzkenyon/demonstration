package com.pd.mongodb;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.pd.mongodb.model.User;
import com.pd.mongodb.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-01-14 15:56
 */
@RestController
public class UserController {
    @Autowired
    private MongoService<User> userMongoService;

    @PostMapping("/insert")
    public User insert(@RequestBody User user){
        userMongoService.insert(user,"user_info");
        return user;
    }

    @PostMapping("/find")
    public List<User> findByConditions(@RequestBody Map<String,Object> conditions){
        return userMongoService.findByCondition("user_info",conditions,User.class,1,10);
    }

    @PutMapping("/update")
    public UpdateResult update(@RequestBody User info){
        Map<String,Object> condition = new HashMap<>();
        condition.put("sex" , 2);
        return userMongoService.update("user_info",condition,info);
    }
    @DeleteMapping("/delete")
    public DeleteResult delete(@RequestBody Map<String,Object> conditions){
        return userMongoService.delete("user_info",conditions);
    }
}
