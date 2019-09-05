package com.hyx.demo.sdk.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.hyx.demo.sdk.bean.SpringContextUtil;
import com.hyx.demo.sdk.enums.SysErrorCode;
import com.hyx.demo.sdk.exception.ServiceException;

/** 
 * @ClassName:HttpUtils <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年9月5日 上午11:00:24 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
public class HttpUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * http post请求(json方式)
     * 
     * @param url 请求地址
     * @param reqJson 请求json字符串
     * @param reqHeader 请求头
     * @return
     * @author liaochangxun 2019年6月3日 下午3:19:34
     */
    public static String httpPostWithJson(String url, String reqJson, Map<String, String> reqHeader) {
        LOGGER.info("调用http接口请求url:{},请求参数:{},请求头:{}", url, reqJson, reqHeader);
        HttpHeaders headers = createJsonHttpHeader();
        if (reqHeader != null) {
            for (Map.Entry<String, String> entry : reqHeader.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<String> entity = new HttpEntity<String>(reqJson, headers);
        ResponseEntity<String> response = getRestTemplate().postForEntity(url, entity, String.class);
        LOGGER.info("调用http接口响应状态码:{},响应参数{}", response.getStatusCodeValue(), response.getBody());
        if (HttpStatus.OK != response.getStatusCode()) {
            LOGGER.error("调用http接口http状态码错误,http状态码:", response.getStatusCodeValue());
            throw new ServiceException(SysErrorCode.HTTP_REQUEST_ERROR.getErrorCode(), SysErrorCode.HTTP_REQUEST_ERROR.getErrorMsg());
        }
        return response.getBody();
    }

    /**
     * http post请求(json方式)
     * 
     * @param url 请求地址
     * @param reqJson 请求json字符串
     * @return
     * @author liaochangxun 2019年6月3日 下午3:19:34
     */
    public static String httpPostWithJson(String url, String reqJson) {
        LOGGER.info("调用http接口请求url:{},请求参数:{}", url, reqJson);
        HttpHeaders headers = createJsonHttpHeader();
        HttpEntity<String> entity = new HttpEntity<String>(reqJson, headers);
        ResponseEntity<String> response = getRestTemplate().postForEntity(url, entity, String.class);
        LOGGER.info("调用http接口响应状态码:{},响应参数{}", response.getStatusCodeValue(), response.getBody());
        if (HttpStatus.OK != response.getStatusCode()) {
            LOGGER.error("调用http接口http状态码错误,http状态码:", response.getStatusCodeValue());
            throw new ServiceException(SysErrorCode.HTTP_REQUEST_ERROR.getErrorCode(), SysErrorCode.HTTP_REQUEST_ERROR.getErrorMsg());
        }
        return response.getBody();
    }

    /**
     * http post请求(表单方式)
     * 
     * @param url
     * @param reqParams 请求参数
     * @param reqHeader 请求头
     * @return
     * @author liaochangxun 2019年6月3日 下午3:19:34
     */
    public static String httpPostWithForm(String url, Map<String, String> reqParams, Map<String, String> reqHeader) {
        LOGGER.info("调用http接口请求url:{},请求参数:{},请求头:{}", url, reqParams, reqHeader);
        HttpHeaders headers = createFormHttpHeader();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        if (params != null) {
            for (Map.Entry<String, String> entry : reqParams.entrySet()) {// 构建表单字段内容
                params.add(entry.getKey(), entry.getValue());
            }
        }
        if (reqHeader != null) {
            for (Map.Entry<String, String> entry : reqHeader.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        ResponseEntity<String> response = getRestTemplate().postForEntity(url, entity, String.class);
        LOGGER.info("调用http接口响应状态码:{},响应参数{}", response.getStatusCodeValue(), response.getBody());
        if (HttpStatus.OK != response.getStatusCode()) {
            LOGGER.error("调用http接口http状态码错误,http状态码:", response.getStatusCodeValue());
            throw new ServiceException(SysErrorCode.HTTP_REQUEST_ERROR.getErrorCode(), SysErrorCode.HTTP_REQUEST_ERROR.getErrorMsg());
        }
        return response.getBody();
    }

    /**
     * http post请求(表单方式)
     * 
     * @param url
     * @param reqParams
     * @return
     * @author liaochangxun 2019年6月3日 下午3:19:34
     */
    public static String httpPostWithForm(String url, Map<String, String> reqParams) {
        LOGGER.info("调用http接口请求url:{},请求参数:{}", url, reqParams);
        HttpHeaders headers = createFormHttpHeader();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        if (params != null) {
            for (Map.Entry<String, String> entry : reqParams.entrySet()) {// 构建表单字段内容
                params.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        ResponseEntity<String> response = getRestTemplate().postForEntity(url, entity, String.class);
        LOGGER.info("调用http接口响应状态码:{},响应参数{}", response.getStatusCodeValue(), response.getBody());
        if (HttpStatus.OK != response.getStatusCode()) {
            LOGGER.error("调用http接口http状态码错误,http状态码:", response.getStatusCodeValue());
            throw new ServiceException(SysErrorCode.HTTP_REQUEST_ERROR.getErrorCode(), SysErrorCode.HTTP_REQUEST_ERROR.getErrorMsg());
        }
        return response.getBody();
    }

    /**
     * http get请求
     * 
     * @param url
     * @return
     * @author liaochangxun 2019年6月3日 下午3:19:34
     */
    public static String httpGet(String url) {
        LOGGER.info("调用http接口请求url:{}", url);
        ResponseEntity<String> response = getRestTemplate().getForEntity(url, String.class);
        LOGGER.info("调用http接口响应状态码:{},响应参数{}", response.getStatusCodeValue(), response.getBody());
        if (HttpStatus.OK != response.getStatusCode()) {
            LOGGER.error("调用http接口http状态码错误,http状态码:", response.getStatusCodeValue());
            throw new ServiceException(SysErrorCode.HTTP_REQUEST_ERROR.getErrorCode(), SysErrorCode.HTTP_REQUEST_ERROR.getErrorMsg());
        }
        return response.getBody();
    }

    public static HttpHeaders createFormHttpHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    public static HttpHeaders createJsonHttpHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    public static RestTemplate getRestTemplate() {
        return SpringContextUtil.getBean("restTemplate", RestTemplate.class);
    }
}
 