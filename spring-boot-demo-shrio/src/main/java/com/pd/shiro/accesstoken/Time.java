/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.pd.shiro.accesstoken;

import java.util.Date;

public class Time {


    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }
}
