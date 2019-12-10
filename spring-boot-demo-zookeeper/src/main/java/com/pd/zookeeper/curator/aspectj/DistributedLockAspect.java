package com.pd.zookeeper.curator.aspectj;

import com.pd.zookeeper.curator.annotation.LockKeyParam;
import com.pd.zookeeper.curator.annotation.ZooLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019-11-29 9:13
 */
@Component
@Aspect
@Slf4j
public class DistributedLockAspect {
    private final CuratorFramework zkClient;
    private static final String KEY_PREFIX = "DISTRIBUTED_LOCK_";
    private static final String KEY_SEPARATOR = "/";
    @Autowired
    public DistributedLockAspect(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    @Pointcut("@annotation(com.pd.zookeeper.curator.annotation.ZooLock)")
    public void doLock(){
    }

    @Around("doLock()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object[] args = point.getArgs();
        ZooLock zooLock = method.getAnnotation(ZooLock.class);
        if(zooLock.key().equals("")){
            throw new RuntimeException("分布式锁键不能为空");
        }
        String lockKey = buildLockKey(zooLock,method,args);
        InterProcessMutex lock = new InterProcessMutex(zkClient,lockKey);
        try{
            if(lock.acquire(zooLock.timeout(),zooLock.timeUnit())){
                return point.proceed();
            }else {
                throw new RuntimeException("请勿重复提交");
            }
        }finally {
            lock.release();
        }
    }

    private String buildLockKey(ZooLock lock,Method method,Object[] args)
            throws NoSuchFieldException,IllegalAccessException{
        StringBuilder key = new StringBuilder(KEY_SEPARATOR + KEY_PREFIX + lock.key());
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for(int i = 0;i< parameterAnnotations.length;i++){
            for(Annotation annotation: parameterAnnotations[i]){
                if(!annotation.annotationType().isInstance(LockKeyParam.class)){
                    continue;
                }
                String[] fields = ((LockKeyParam)annotation).fields();
                if(fields.length == 0){
                    if(args[i] != null)
                        throw  new RuntimeException("动态参数不能为null");
                    key.append(KEY_SEPARATOR).append(args[i]);
                }else {
                    for(String field : fields){
                        Class<?> clazz = args[i].getClass();
                        Field declarField = clazz.getDeclaredField(field);
                        declarField.setAccessible(true);
                        Object value = declarField.get(clazz);
                        key.append(KEY_SEPARATOR).append(value);
                    }
                }

            }
        }
        return key.toString();
    }
}
