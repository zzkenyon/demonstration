package com.pd.zuul.auth.service.impl;

import com.google.common.io.BaseEncoding;
import com.pd.zuul.auth.service.TokenService;
import com.pd.zuul.auth.util.Time;
import com.pd.zuul.auth.util.Number;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.RuntimeCryptoException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;


import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
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
/**
 * JWT
 * https://baijiahao.baidu.com/s?id=1608021814182894637&wfr=spider&for=pc
 */
public class TokenServiceImpl implements TokenService {
    /**
     * 时效
     */
    private static final int TOKEN_EXPIRE_TIME = 24*60*60;
    /**
     * 黑名单
     */
    private static final String TOKEN_BLOCK_LIST="access:blocktoken";

    @Override
    public int verify(String token) {
        // "AAABUFuFcbYORi4Hpspa2Eti769QclDdmh52H1phI7MZND2rmP5XHo3w";
        String str = token;
        //base64解码
        str = decodeBase64(str);
        //判断token是否有效
        if( str.length() <= 28 ) {
            return 1;
        }
        String sign = hMacSha256(Number.hexStringToBytes(str.substring(0, 28)));
        if( !str.substring(28, str.length()).equals(sign) ) {
            return 1;
        }
        //判断 token 是否过期
        int timeStamp = parseInt(str.substring(16,24),16);
        if(timeStamp < Time.getSecondTimestamp(new Date())) {
            return 2;
         }
        return 0;
    }
    @Override
    public String generateAccessToken(String id){
        String uid = Number.getHexStr(parseInt(id), 8);
        String signTime = Number.getHexStr(Time.getSecondTimestamp(new Date()), 8);
        String expireTime = Number.getHexStr(Time.getSecondTimestamp(new Date())+TOKEN_EXPIRE_TIME, 8);
        // TODO 修改为安全随机函数
        String random = Number.getHexStr(parseInt(Number.getRandom(4)), 4);
        String sign = hMacSha256(Number.hexStringToBytes(uid + signTime + expireTime + random));
        String token = encodeBase64(Number.hexStringToBytes(uid + signTime + expireTime + random + sign));
        return token;
    }

    private String hMacSha256(byte[] content) {
        String secret = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
        byte[] secretByte = Number.hexStringToBytes(secret);

        try {
            Security.addProvider(new BouncyCastleProvider());
            SecretKey secretKey = new SecretKeySpec(secretByte,
                    "HmacSHA256");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            byte[] digest = mac.doFinal(content);
            return new HexBinaryAdapter().marshal(digest);
        } catch (Exception e) {
            throw new RuntimeCryptoException("加密异常");
        }
    }


    @Override
    public int getUserId(String token) {
        int id = parseInt(decodeBase64(token).substring(0, 8), 16);
        return id;
    }


    private String encodeBase64(byte[] b) {
        String s = null;
        if (b != null) {
            s = BaseEncoding.base64().encode(b);
        }
        return s;
    }


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

    public static void main(String[] args) {
        TokenService tokenService = new TokenServiceImpl();
        String token = tokenService.generateAccessToken("1");
        System.out.println(tokenService.getUserId(token));
    }
}
