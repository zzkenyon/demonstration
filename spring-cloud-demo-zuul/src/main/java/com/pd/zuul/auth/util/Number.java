/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.pd.zuul.auth.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author zhaozhengkang
 */
public class Number {

    public static String getHexStr(int num) {
        return Integer.toHexString(num);
    }
    // 指定十六进制位数，前补0
    public static String getHexStr(int num, int length) {
        String str = Integer.toHexString(num);
        if(str.length() < length ){
            int t = length - str.length();
            while( t-- > 0){
                str = "0" + str;
            }
        }
        return str;
    }
    public static String getHexStr(String num) {
        return Integer.toHexString(Integer.valueOf(num));
    }

    //十六进制字符串转byte
    public static byte[] hexStringToBytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }
        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes;
    }

    //字节数组转十六进制字符串
    public static String bytesToHexString(byte[] bytes){
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for(byte b : bytes) { // 使用String的format方法进行转换
            buf.append(String.format("%02x", new Integer(b & 0xff)));
        }

        return buf.toString();
    }


    // byte数组合并
    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[byte_1.length + byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }


    public static String getSalt() {
        String sequence = "1234567890abcdefghijklmnopqrstuvwxyz";
        int length = 6;
        String str = "";
        for (int a = 0; a < length; a++) {
            int t = (int) (Math.random()*sequence.length());
            char ch = sequence.charAt(t);
            str = str + String.valueOf(ch);
        }
        return str;
    }

    public static String getRandom(int length) {
        String val = "";
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            for (int i = 0; i < length; i++) {
                val += String.valueOf(random.nextInt(10));
            }
            return val;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                val += String.valueOf(random.nextInt(10));
            }
            return val;
        }
    }

}
