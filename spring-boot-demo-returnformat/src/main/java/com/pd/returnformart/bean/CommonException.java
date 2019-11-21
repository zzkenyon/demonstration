package com.pd.returnformart.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author zhaozhengkang@cetiti.com
 * @description
 * 错误码说明："https://blog.csdn.net/tyyytcj/article/details/78528499"
 *  * 第1位表示错误提示级别
 *  *  |-1 按服务器返回提示
 *  *  |-2 按不提示
 *  *  |-3 需要进行友好提示(卖萌)
 *  * 第2-3位表示服务代码
 *  *  |-00 系统错误(一般为后端bug或故障导致，或多个服务通用的错误)
 *  *  |-01 用户权限问题
 *  *  |-02 用户参数输入错误
 *  *  |-03 其它服务错误（名称已经存在等等）
 *  * 第4-7位表示具体错误
 *  *  |-xx 具体的错误代码
 * @date 2019/11/20 14:55
 */
@AllArgsConstructor
@Getter
public enum CommonException {

    COMMON_SUCCESS(new HttpException(0, HttpStatus.OK, "请求成功")),
    COMMON_FAILED(new HttpException(3000400, HttpStatus.INTERNAL_SERVER_ERROR, "未知原因失败")),
    COMMON_STRONG_FORBIDDEN(new HttpException(1000401, HttpStatus.FORBIDDEN, "访问被拒绝")),
    COMMON_SERVICE_UNHEALTHY(new HttpException(3000403, HttpStatus.SERVICE_UNAVAILABLE, "服务暂时不可用")),
    COMMON_NOT_FOUND(new HttpException(2000404, HttpStatus.NOT_FOUND, "接口不存在")),
    COMMON_METHOD_FORBIDDEN(new HttpException(2000405, HttpStatus.METHOD_NOT_ALLOWED, "不支持的HTTP Method")),
    COMMON_PARAMS_ERROR(new HttpException(3001001, HttpStatus.OK, "参数不正确")),
    COMMON_BAD_REQUEST(new HttpException(3000400, HttpStatus.BAD_REQUEST, "请求格式不正确")),
    COMMON_RESOURCE_FOUND(new HttpException(3001404, HttpStatus.NOT_FOUND, "请求资源数据不存在")),
    COMMON_UNSUPPORTED_MEDIA_TYPE(new HttpException(3001415, HttpStatus.UNSUPPORTED_MEDIA_TYPE, "不支持的content-type"));

    private HttpException httpException;

}
