package com.pd.zuul.auth.token;/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 11:32
 */

import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authc.AuthenticationToken;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/3/23 11:32
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
@Data
public class PhoneSmsToken implements AuthenticationToken {
    public static final String REGEXP_PHONE = "1[3456789]\\d{9}";
    private static final Pattern PATTERN_PHONE = Pattern.compile("^"+ REGEXP_PHONE + "$");
    private String phone;
    private String code;
    public PhoneSmsToken(String userName, String code) {
        this.code = code;
        if(isPhone(phone)){
            this.phone = phone;
        }else{
            // TODO 抛出手机号格式不正确异常
        }
    }

    private boolean isPhone(String phone) {
        if(phone.length() != 11){
            return false;
        }
        Matcher matcher = PATTERN_PHONE.matcher(phone);
        return matcher.matches();
    }

    @Override
    public Object getPrincipal() {
        return getPhone();
    }

    @Override
    public Object getCredentials() {
        return getCode();
    }


}
