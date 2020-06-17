package com.pd.zuul.auth.realm;

import com.pd.zuul.auth.model.User;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.format.HexFormat;
import org.apache.shiro.crypto.hash.format.ParsableHashFormat;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/15 09:25
 */
@Component
public class PasswordHelper {
    private final RandomNumberGenerator randomNumberGenerator =  new SecureRandomNumberGenerator();
    public final static String PRIVATE_SALT = ByteSource.Util.bytes("jungomama").toHex();
    private static final String algorithmName = "md5";
    private static final int hashIterations = 2;

    /**
     * 加密密码  在添加用户、充值密码的时候调用
     * @param user
     */
    public void encryptPassword(User user) {
        user.setPublicSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getPublicSalt() + PRIVATE_SALT),
                hashIterations).toHex();
        user.setPassword(newPassword);
    }

    public static void main(String[] args) {
        DefaultPasswordService ps = new DefaultPasswordService();
        DefaultHashService hs = new DefaultHashService();
        hs.setHashAlgorithmName("md5");
        hs.setHashIterations(2);
        hs.setPrivateSalt(ByteSource.Util.bytes("jungomama"));
        ps.setHashService(hs);
        ps.setHashFormat(new HexFormat());
        System.out.println(ps.encryptPassword("zzk"));
    }
}
