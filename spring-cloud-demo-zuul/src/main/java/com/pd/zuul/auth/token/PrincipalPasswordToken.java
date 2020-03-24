package com.pd.zuul.auth.token;/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 11:24
 */

import org.apache.shiro.authc.AuthenticationToken;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/3/23 11:24
 */
public class PrincipalPasswordToken implements AuthenticationToken {

    public static final String REGEXP_PHONE = "1[3456789]\\d{9}";
    public static final String REGEXP_EMAIL = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+";
    public static final String REGEXP_USERNAME = "^\\d{6}[0-9a-zA-Z]+$";

    public static final int TYPE_USERNAME = 0;
    public static final int TYPE_PHONE = 1;
    public static final int TYPE_EMAIL = 2;
    private static final Pattern PATTERN_PHONE = Pattern.compile("^"+ REGEXP_PHONE+"$");
    private static final Pattern PATTERN_EMAIL = Pattern.compile("^"+ REGEXP_EMAIL+"$");

    /**
     * 用户唯一标识
     * 用户名, 邮箱或手机号(不含昵称)
     * 有效性校验在控制层保证，此处不处理
     */
    private String principal;
    /**
     * 唯一标识类型
     */
    private int type;
    private String password;

    public PrincipalPasswordToken(String userName, String password) {
        this.principal = userName;
        this.password = password;
        if(isPhone(principal)){
            this.type = TYPE_PHONE;
        }else if(isEmail(principal)){
            this.type = TYPE_EMAIL;
        }else{
            this.type = TYPE_USERNAME;
        }
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

    private boolean isPhone(String principal) {
        Matcher matcher = PATTERN_PHONE.matcher(principal);
        return matcher.matches();
    }

    private boolean isEmail(String principal) {
        Matcher matcher = PATTERN_EMAIL.matcher(principal);
        return matcher.matches();
    }

}
