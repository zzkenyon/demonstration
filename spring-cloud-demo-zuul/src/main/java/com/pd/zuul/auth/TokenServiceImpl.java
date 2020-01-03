package com.pd.zuul.auth;

import com.google.common.io.BaseEncoding;
import com.pd.zuul.auth.util.Time;
import com.pd.zuul.auth.util.Number;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.RuntimeCryptoException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;


import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.Security;
import java.util.Date;

import static java.lang.Integer.parseInt;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/1/3 16:32
 */
@Service
@Slf4j
public class TokenServiceImpl implements TokenService {
    /**
     * 时效
     */
    private static final int TOKEN_EXPIRE_TIME = 24*60*60;
    /**
     * 黑名单
     */
    private static final String TOKEN_BLOCK_LIST="ipbank:blocktoken";
    @Override
    public int verify(String token) {
        String str = token; // "AAABUFuFcbYORi4Hpspa2Eti769QclDdmh52H1phI7MZND2rmP5XHo3w";
        //base64解码
        str = decodeBase64(str);
        //判断token是否有效
        if( str.length() <= 28 ) {
            return 1;
        }
        String sign = HMACSHA256(Number.hexStringtoBytes(str.substring(0, 28)));
        if( !str.substring(28, str.length()).equals(sign) ) {
            return 1;
        }
        // 判断token是否已经退出
        /*if(redisService.get(TOKEN_BLOCK_LIST, token) != null){
            return 1;
        }*/

        //判断 token 是否过期
        int timestamp = parseInt(str.substring(16,24),16);
        if(timestamp < Time.getSecondTimestamp(new Date())) {
            return 2;
        }
        return 0;
    }

    @Override
    public String generateToken(String id, int spanTime) {
        String uid = Number.getHexStr(parseInt(id), 8);
        String signtime = Number.getHexStr(Time.getSecondTimestamp(new Date()), 8);
        String expiretime = Number.getHexStr(Time.getSecondTimestamp(new Date())+spanTime, 8);
        // TODO 修改为安全随机函数
        String random = Number.getHexStr(parseInt(Number.getRandom(4)), 4);
        String sign = HMACSHA256(Number.hexStringtoBytes(uid + signtime + expiretime + random));
        String token = encodeBase64(Number.hexStringtoBytes(uid + signtime + expiretime + random + sign));
        return token;
    }

    @Override
    public boolean logout(String token) {
        if(token != null && verify(token) == 0) {
            int expire_timestamp = parseInt(decodeBase64(token).substring(16,24),16);
            int now_timestamp = Time.getSecondTimestamp(new Date());
            int expire_time = expire_timestamp - now_timestamp;
            /*redisService.put(TOKEN_BLOCK_LIST, token, String.valueOf(expire_timestamp), expire_time);*/
            return true;
        }
        return false;
    }

    @Override
    public String getTokenFromCookie(Cookie[] cookie) {
        String token = null;
        if(cookie != null) {
            for (Cookie c : cookie) {
                if(c.getName().equals("IB_TOKEN")){
                    token = c.getValue();
                }
            }
        }
        return token;
    }

    @Override
    public String getUsername(String token) {
        String username = null;
        if(verify(token) == 0) {
            int id = getUserId(token);
            /*username = userMapper.getUserById(id).getUsername();*/
        }
        return username;
    }

    @Override
    public int getUserId(String token) {
        int id = parseInt(decodeBase64(token).substring(0, 8), 16);
        return id;
    }

    private String HMACSHA256(byte[] content) {
        String secret = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
        byte[] secretByte = Number.hexStringtoBytes(secret);

        try {
            Security.addProvider(new BouncyCastleProvider());
            SecretKey secretKey = new SecretKeySpec(secretByte,
                    "HmacSHA256");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            // byte[] digest = mac.doFinal(content.getBytes("UTF-8"));
            byte[] digest = mac.doFinal(content);
            return new HexBinaryAdapter().marshal(digest);
        } catch (Exception e) {
            throw new RuntimeCryptoException("加密异常");
        }
    }

    private String encodeBase64(byte[] b) {
        String s = null;
        if (b != null) {
            s = BaseEncoding.base64().encode(b);
        }
        return s;
    }

    // 解密
    private String decodeBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            try {
                b = BaseEncoding.base64().decode(s);
                result = Number.bytesToHexString(b);
                // result = new String(b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toUpperCase();
    }
}
