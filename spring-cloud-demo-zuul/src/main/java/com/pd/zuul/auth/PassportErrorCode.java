package com.pd.zuul.auth;

import com.pd.zuul.common.HttpErrorCode;
import com.pd.zuul.common.HttpException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 16:50
 */
@AllArgsConstructor
@Getter
public enum PassportErrorCode implements HttpErrorCode {
    /**
     *
     */
    SMS_RATE_LIMIT(new HttpException(1010101, HttpStatus.OK, "短信发送频率过高")),
    NOT_LOGIN(new HttpException(2*1000000+1*10000+210, HttpStatus.UNAUTHORIZED, "用户未登录")),
    PASSPORT_OTHER(new HttpException(2*1000000+1*10000+210, HttpStatus.UNAUTHORIZED, "其它鉴权异常")),
    TOKEN_INVALID(new HttpException(2010208, HttpStatus.UNAUTHORIZED, "无效的Token")),
    TOKEN_EXPIRED(new HttpException(3010207, HttpStatus.UNAUTHORIZED, "Token已过期")),
    PHONE_NOT_FOUND(new HttpException(1010205, HttpStatus.OK, "手机号未注册")),
    SMS_FAILED(new HttpException(1010102, HttpStatus.OK, "短信验证码不正确")),
    PASSWORD_FAILED(new HttpException(1010202, HttpStatus.OK, "密码错误")),
    USER_NOTFOUND(new HttpException(1010201, HttpStatus.OK, "用户不存在")),
    USERNAME_EXIST(new HttpException(1010204, HttpStatus.OK, "用户名已存在")),
    USER_LOCKED(new HttpException(1010210, HttpStatus.OK, "用户已被禁用")),
    PASSWORD_FAILED_LIMIT(new HttpException(1010203, HttpStatus.OK, "密码错误过多，临时限制登录")),
    PHONE_EXIST(new HttpException(1010206, HttpStatus.OK, "手机号已注册")),
    AGENCY_EXIST(new HttpException(2010209, HttpStatus.OK, "机构已存在")),
    USER_LOGOUT(new HttpException(0, HttpStatus.FOUND, "用户已退出"));

    private HttpException httpException;
}
