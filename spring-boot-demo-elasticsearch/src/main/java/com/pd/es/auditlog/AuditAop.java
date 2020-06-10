package com.pd.es.auditlog;

import com.alibaba.fastjson.JSON;
import com.pd.es.auditlog.annotation.Audit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringEscapeUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/9 09:25
 */
@Aspect
@Component
@Slf4j
public class AuditAop {
    @Value("${spring.application.name}")
    private String appName;
    ThreadLocal<AuditMessage> auditMessage = new ThreadLocal<>();
    // 声明AOP切入点
    @Pointcut("@annotation(com.pd.es.auditlog.annotation.Audit)")
    public void audit() {
    }

    @Around("audit()")
    public Object aroundExec(ProceedingJoinPoint pjp) throws Throwable {
        try {
            AuditMessage am = new AuditMessage();
            am.setModuleName(appName);
            am.setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            am.setRequestUri(request.getRequestURI());
            am.setRequestQueryStr(request.getQueryString());
            am.setRequestMethod(request.getMethod());
            am.setIp(getIpAddr(request));
            String userId;
            if((userId=request.getHeader("uid")) != null) {
                am.setUserId(userId);
            }
            MethodSignature ms = (MethodSignature) pjp.getSignature();
            Method method = ms.getMethod();
            Audit audit = method.getAnnotation(Audit.class);
            am.setOperate(audit.operate());
            RequestBodyTuple rbt = getRequestBody(pjp,method);
            if(rbt != null) {
                am.setRequestBody(rbt.getParam());
                am.setRequestBodyType(rbt.paramType.getName());
            }
            auditMessage.set(am);
        } catch (Exception e) {
            log.error("Error occured while auditing, cause by: ", e);
        }
        Object rtn = pjp.proceed();
        return rtn;
    }

    /**
     * 一个controller只能有一个requestbody
     * @param pjp
     * @return
     */
    private RequestBodyTuple getRequestBody(ProceedingJoinPoint pjp, Method method){
        Object[] args = pjp.getArgs();
        Annotation[][] paramsAnnotations = method.getParameterAnnotations();
        Parameter[] parameters = method.getParameters();
        for(int i = 0; i < paramsAnnotations.length; i++){
            for(int j = 0; j < paramsAnnotations[i].length; j++){
                if(paramsAnnotations[i][j] instanceof RequestBody){
                    return new RequestBodyTuple(parameters[i].getType(),args[i]);
                }
            }
        }
        return null;
    }
    @Data
    @AllArgsConstructor
    private class RequestBodyTuple {
        private Class<?> paramType;
        private Object param;
    }

    /**
     * 带参返回
     */
    @AfterReturning(pointcut = "audit()", returning = "rc")
    public void doAfterReturning(ResponseEntity rc) {
        try {
            // 返回码SUCCESS才去审计记录
            if (rc.getStatusCode().equals(HttpStatus.OK)) {
                if (null == auditMessage.get()) {
                    log.warn("Warning occured, because msg is null!");
                } else {
                    AuditMessage ams = auditMessage.get();
                    ams.setResult(rc.getStatusCode());
                    String js = StringEscapeUtils.unescapeJava(JSON.toJSONString(ams));
                    log.info(js);
                }
            }
        } catch (Exception e) {
            log.warn("Warning occured while afterReturning, cause by: ", e);
        }
    }


    @AfterThrowing(pointcut = "audit()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Error occured, cause by {}", e.getMessage());
    }

    /**
     * 不带参返回
     */
    /*@AfterReturning(pointcut = "audit()")
    public void doAfterReturning(JoinPoint joinPoint) {
        log.debug("afterReturning without returnType..");
    }*/

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
