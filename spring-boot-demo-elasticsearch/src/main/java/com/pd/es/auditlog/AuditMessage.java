package com.pd.es.auditlog;

import lombok.Data;
import org.springframework.http.HttpStatus;


/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/9 09:33
 */
@Data
public class AuditMessage {
    private String userId;
    private String ip;
    private String requestMethod;
    private String requestQueryStr;
    private String moduleName;
    private String operate;
    private String requestUri;
    private Object requestBody;
    private String requestBodyType;
    private String operateTime;
    private HttpStatus result;
}
