package com.pd.zuul.auth.util;


import com.pd.zuul.auth.TokenService;

import javax.servlet.http.Cookie;
import java.util.Map;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-01-04 13:50
 */
public class Jwt implements TokenService {
    private Header header;
    public void setHeader(Header header){
        this.header = header;
    }

    public String getAlg(){
        return this.header.alg;
    }
    private Map<String,String> body;

    public void addToBody(String key,String value){
        this.body.put(key,value);
    }
    public void addToBody(Map<String,String> info){
        this.body.putAll(info);
    }

    private String generateJsonString(){
        return  null;
    }

    @Override
    public int verifyToken(String token) {
        return 0;
    }

    @Override
    public String generateToken() {
        return generateToken(Algorithm.HS256);
    }

    @Override
    public String generateToken(Algorithm alg) {
        return null;
    }

    @Override
    public boolean logout(String token) {
        return false;
    }

    @Override
    public String getTokenFromCookie(Cookie[] cookie) {
        return null;
    }

    @Override
    public String getUsername(String token) {
        return null;
    }

    @Override
    public int getUserId(String token) {
        return 0;
    }


    class Header{
        private final String typ = "JWT";
        private String alg = "HS256";

    }
}
