package com.hyx.demo.sdk.utils;

import org.apache.commons.lang3.StringUtils;

/** 
 * @ClassName:MosaicUtils <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年9月5日 上午11:27:00 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
public class MosaicUtils {
    public static String mosaicName(String fullName) {
        if (StringUtils.isBlank(fullName)) {
            return "";
        }
        String name = StringUtils.right(fullName, 1);
        return StringUtils.leftPad(name, fullName.length(), "*");
    }

    public static String mosaicCard(String str) {
        String ragex = "(?<=\\d{6})\\d(?=\\d{4})";
        return str.replaceAll(ragex, "*");
    }

    public static String mosaicPhone(String str) {
        String ragex = "(?<=[\\d]{3})\\d(?=[\\d]{4})";
        return str.replaceAll(ragex, "*");
    }

    public static String mosaicId(String str) {
        String hideStr = "";
        int len = str.length();
        if (len <= 4) {
            return str;
        }
        String last4Str = StringUtils.substring(str, len - 4);
        String remainStr = StringUtils.substring(str, 0, len - 4);
        int remainLen = remainStr.length();
        if (remainLen <= 6) {
            return str;
        }
        String middleStr = StringUtils.substring(remainStr, 6, remainLen);
        for (int i = 0; i < middleStr.length(); i++) {
            hideStr = hideStr + "*";
        }
        hideStr = StringUtils.substring(remainStr, 0, 6) + hideStr + last4Str;

        return hideStr;
    }
}