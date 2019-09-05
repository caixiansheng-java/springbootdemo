package com.hyx.demo.sdk.utils;  

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/** 
 * @ClassName:MDCUtil <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年9月5日 上午11:11:30 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
public class MDCUtil {
    public static final String TRADENUM = "tradeNum";
    public static final String IP = "IP";

    /**
     * @param tradeNum
     * @param request
     */
    public static void init(String tradeNum, HttpServletRequest request) {
        MDC.remove(TRADENUM);
        MDC.remove(IP);
        if (StringUtils.isNotBlank(tradeNum)) {
            MDC.put(TRADENUM, tradeNum);
        } else {
            MDC.put(TRADENUM, UUID.randomUUID().toString().replace("-", ""));
        }
        MDC.put(IP, getIpAddress(request));
    }

    /**
     * @param tradeNum
     * @param ip
     */
    public static void init(String tradeNum, String ip) {
        MDC.remove(TRADENUM);
        MDC.remove(IP);
        if (StringUtils.isNotBlank(tradeNum)) {
            MDC.put(TRADENUM, tradeNum);
        } else {
            MDC.put(TRADENUM, UUID.randomUUID().toString().replace("-", ""));
        }
        MDC.put(IP, ip);
    }

    /**
     * @return
     */
    public static String getTradeNum() {
        return MDC.get(TRADENUM);
    }

    /**
     * @return
     */
    public static String getIp() {
        return MDC.get(IP);
    }

    /**
     * @param request
     * @return
     */
    public static final String getIpAddress(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }
}
	
 