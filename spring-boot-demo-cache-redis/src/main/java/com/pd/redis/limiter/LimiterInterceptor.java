package com.pd.redis.limiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/4 14:50
 */
@Slf4j
public class LimiterInterceptor implements HandlerInterceptor {
    private static final String REDIS_KEY_PREFIX = "api-limiter-";
    @Autowired
    private HashOperations<String,String,Object> hOptions;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            if(!method.isAnnotationPresent(AccessLimiter.class)){
                return true ;
            }
            AccessLimiter accessLimiter = method.getAnnotation(AccessLimiter.class);
            String uid = request.getHeader("uid");
            if(uid == null){
                uid = "test";
            }
            String uri = request.getRequestURI();
            return nonamenow(response,uid,uri,accessLimiter);
        }
        return true;
    }

    private boolean nonamenow(HttpServletResponse response, String uid, String uri, AccessLimiter anno) throws IOException{
        String redisKey = REDIS_KEY_PREFIX+uid;

        Bucket bucket = (Bucket) hOptions.get(redisKey, uri);
        int initToken = getInitialToken(anno);
        if(bucket == null){
            hOptions.put(redisKey,uri, new Bucket(System.currentTimeMillis(), initToken - 1));
            return true;
        }else {
            if(bucket.getToken() == 0){
                long nowTime = System.currentTimeMillis();
                long pastTime = nowTime - bucket.getLastUpdate();
                bucket.setLastUpdate(nowTime);
                bucket.setToken(calculateToken(pastTime,initToken));
                long pastToken = calculateToken(pastTime,initToken);
                if(pastToken + bucket.getToken() >= initToken){
                    bucket.setToken(initToken);
                }else {
                    bucket.setToken(pastToken + bucket.getToken());
                }
                hOptions.put(redisKey,uri,bucket);
                output(response, "请求太频繁!");
                log.info("请求太频繁!");
                return false;
            }else {
                long nowTime = System.currentTimeMillis();
                long pastTime = nowTime - bucket.getLastUpdate();
                bucket.setLastUpdate(nowTime);
                bucket.setToken(calculateToken(pastTime,initToken));
                long pastToken = calculateToken(pastTime,initToken);
                if(pastToken + bucket.getToken() >= initToken){
                    bucket.setToken(initToken - 1);
                }else {
                    bucket.setToken(pastToken + bucket.getToken() - 1);
                }
                hOptions.put(redisKey,uri,bucket);
                return true;
            }
        }
    }

    private long calculateToken(long pastTime, int initToken) {
        return initToken*pastTime/1000;
    }

    private int getInitialToken(AccessLimiter anno) {
        int perMin = anno.perMinter();
        int perSec = anno.perSec();
        int perHour = anno.perHour();
        if (perSec != 0) {
            return perSec;
        }
        if (perMin != 0) {
            return perMin / 60;
        }
        if (perHour != 0) {
            return perHour / 3600;
        }
        return Integer.MAX_VALUE;
    }

    public void output(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }
}
