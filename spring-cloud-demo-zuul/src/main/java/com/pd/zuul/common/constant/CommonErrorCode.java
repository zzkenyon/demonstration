package com.pd.zuul.common.constant;

import com.pd.zuul.common.HttpErrorCode;
import com.pd.zuul.common.HttpException;
import lombok.Getter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * ErrorCode
 * ipbank异常枚举
 * @author chenzhiqiang@cetiti.com
 *
 * 错误码说明："https://blog.csdn.net/tyyytcj/article/details/78528499"
 * 第1位表示错误提示级别
 *  |-1 按服务器返回提示
 *  |-2 按不提示
 *  |-3 需要进行友好提示(卖萌)
 * 第2-3位表示服务代码
 *  |-00 系统错误(一般为后端bug或故障导致，或多个服务通用的错误)
 *  |-01 用户权限问题
 *  |-02 用户参数输入错误
 *  |-03 其它服务错误（名称已经存在等等）
 * 第4-7位表示具体错误
 *  |-xx 具体的错误代码
 *
 * @author liuzhaoqi@cetiti.com
 * @since 0.1.0
 */
@AllArgsConstructor
@Getter
public enum CommonErrorCode implements HttpErrorCode {

    /**
     *
     */
    COMMON_SUCCESS(new HttpException(0, HttpStatus.OK, "请求成功")),
    COMMON_STRONG_FORBIDDEN(new HttpException(2000100, HttpStatus.FORBIDDEN, "访问被拒绝")),
    COMMON_PARAMS_ERROR(new HttpException(2000301, HttpStatus.OK, "参数格式不正确")),
    COMMON_DATA_NOTFOUND(new HttpException(2000304, HttpStatus.OK, "请求数据不存在")),
    COMMON_BAD_REQUEST(new HttpException(4000400, HttpStatus.BAD_REQUEST, "请求格式不正确")),
    COMMON_JSON_FORMAT_ERROR(new HttpException(4000601, HttpStatus.BAD_REQUEST, "JSON格式不正确")),
    COMMON_NOT_FOUND(new HttpException(4000404, HttpStatus.NOT_FOUND, "接口不存在")),
    COMMON_METHOD_FORBIDDEN(new HttpException(4000405, HttpStatus.METHOD_NOT_ALLOWED, "不支持的HTTP Method")),
    COMMON_UNSUPPORTED_MEDIA_TYPE(new HttpException(4000415, HttpStatus.UNSUPPORTED_MEDIA_TYPE, "不支持的content-type")),
    COMMON_DATASOURCE_CONNECTION(new HttpException(5000100, HttpStatus.SERVICE_UNAVAILABLE, "数据库连接异常")),
    COMMON_DATASOURCE_CONNECTION_CLOSE(new HttpException(5000101, HttpStatus.SERVICE_UNAVAILABLE, "数据库连接异常")),
    COMMON_SERVICE_UNHEALTHY_502(new HttpException(5000502, HttpStatus.SERVICE_UNAVAILABLE, "服务暂时不可用")),
    COMMON_SERVICE_UNHEALTHY_503(new HttpException(5000503, HttpStatus.SERVICE_UNAVAILABLE, "服务暂时不可用")),
    COMMON_SERVICE_UNHEALTHY_504(new HttpException(5000504, HttpStatus.SERVICE_UNAVAILABLE, "服务暂时不可用")),
    COMMON_FAILED(new HttpException(6000000, HttpStatus.INTERNAL_SERVER_ERROR, "未知原因失败")),
    COMMON_NULL_POINTER(new HttpException(6000001, HttpStatus.INTERNAL_SERVER_ERROR, "")),
    COMMON_DATASOURCE_UNKNOWN(new HttpException(6000100, HttpStatus.INTERNAL_SERVER_ERROR, "未识别的数据库异常")),
    COMMON_BAD_SQL(new HttpException(6000101, HttpStatus.INTERNAL_SERVER_ERROR, "SQL语法错误")),
    COMMON_DATASOURCE_SELECTONE(new HttpException(6000102, HttpStatus.INTERNAL_SERVER_ERROR, "数据库返回结果不符合预期"));

    private HttpException httpException;

    @Override
    public HttpException getHttpException() {
        return this.httpException;
    }
}

