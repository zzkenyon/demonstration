package com.pd.es.auditlog;

import com.pd.es.auditlog.annotation.Audit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/9 13:11
 */
@RestController
public class AuditTestController {

    @GetMapping("/audit/test")
    @Audit(operate = "SEARCH-User")
    public ResponseEntity<User> auditTest(){
        User user = new User();
        user.setAge(1);
        user.setName("zzk");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/audit/post")
    @Audit(operate = "INSERT-User")
    public ResponseEntity<User> auditPost(@RequestBody User user){
        //TODO
        return ResponseEntity.ok(user);
    }

}
