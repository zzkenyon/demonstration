package com.pd.returnformart.config;

import com.pd.returnformart.bean.*;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaozhengkang@cetiti.com
 * @description
 * @date 2019/11/20 14:51
 */
@RestControllerAdvice
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    /*@Value("${app.mode}")
    private String mode;*/

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        // Controller可使用ResponseEntity来修改返回头部, Cookie等, 这种情况下返回值由RestResponseEntity封装
        // 因beforeBodyWrite中body只是ResponseEntity的一部分, 没有足够的信息对返回值进行全局处理
        if("org.springframework.http.ResponseEntity".equals(returnType.getGenericParameterType().getTypeName())){
            return false;
        }

        // 因Json格式返回的数据均通过FastJsonHttpMessageConverter转换,
        // 在此排除对其它类型返回数据的误处理, 例如text/html类型
        if(!"com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter".equals(converterType.getTypeName())){
            return false;
        }

        String className = returnType.getContainingClass().getCanonicalName();
        return className.startsWith("com.pd");
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<?
            extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        return new RestResponseBody(ResponseMeta.SUCCESS_META, body);
    }

    /**
     * 业务代码中手动抛出自定义异常的统一处理
     *
     * @param e 预定义HttpException
     * @return
     */
    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity handleHttpException(HttpException e){
        return RestResponseEntity.error(e);
    }

    /**
     * 违反参数约束条件的异常统一处理
     *
     * @param e 预定义 ConstraintViolationException
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e){
        HttpException exception = CommonException.COMMON_PARAMS_ERROR.getHttpException();
        return RestResponseEntity.error(exception.getCode(),e.getMessage(),exception.getHttpStatus());
    }

    /**
     * 参数类型错误的异常统一处理
     * *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){

        // 属性名
        StringBuilder msg = new StringBuilder();
        msg.append(e.getName());
        msg.append(":");
        String type = e.getRequiredType().getTypeName();
        int i = type.lastIndexOf('.')+1;
        msg.append(type.substring(i));
        msg.append(", ");
        msg.append("参数类型应为: ");
        msg.append(type.substring(i));

        HttpException exception = CommonException.COMMON_PARAMS_ERROR.getHttpException();
        return RestResponseEntity.error(exception.getCode(),msg.toString(),exception.getHttpStatus());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        HttpException exception = CommonException.COMMON_BAD_REQUEST.getHttpException();
        String message;
        if("Required request body is missing".equals(e.getMessage().substring(0, e.getMessage().indexOf(':')))){
            message = "Body不能为空";
        }
        else{
            message = "";
        }
        return RestResponseEntity.error(exception.getCode(),message,exception.getHttpStatus());
    }

    /**
     * 校验器校验失败的统一异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        HttpException exception = CommonException.COMMON_PARAMS_ERROR.getHttpException();

        List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
            invalidArgument.setMessage(error.getDefaultMessage());
            invalidArgument.setField(error.getField());
            // 生产环境不增加此项, 防止利用此项进行XSS攻击
           /* if("dev".equals(mode)) {
                invalidArgument.setRejectedValue(error.getRejectedValue());
            }*/
            invalidArguments.add(invalidArgument);
        }
        return RestResponseEntity.errorBuilder(exception)
                .body(invalidArguments);
    }
}

