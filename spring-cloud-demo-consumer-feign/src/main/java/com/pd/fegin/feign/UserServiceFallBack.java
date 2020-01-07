package com.pd.fegin.feign;

import com.pd.fegin.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/7 10:22
 */
@Service
@Slf4j
public class UserServiceFallBack implements UserServiceFeignClient {
    @Override
    public User getUser(Long id) {
        log.info("断路器已打开");
        return new User(0L,"null","null");
    }
}
