package com.hyx.demo.sdk.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.hyx.demo.sdk.enums.SysErrorCode;
import com.hyx.demo.sdk.exception.ServiceErrorCode;
import com.hyx.demo.sdk.exception.ServiceException;

/** 
 * @ClassName:BaseResp <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年9月5日 下午3:54:41 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
public class BaseResp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 返回码
     */
    protected String returnCode;
    /**
     * 返回信息
     */
    protected String returnMsg;

    public BaseResp() {
        this.returnCode = SysErrorCode.SUCCESS.getErrorCode();
        this.returnMsg = SysErrorCode.SUCCESS.getErrorMsg();
    }

    public BaseResp(Exception e) {
        if (e instanceof ServiceException) {
            ServiceException e1 = (ServiceException) e;
            this.returnCode = e1.getCode();
            this.returnMsg = e1.getMessage();
        } else {
            this.returnCode = SysErrorCode.SYSTEM_ERROR.getErrorCode();
            this.returnMsg = SysErrorCode.SYSTEM_ERROR.getErrorMsg();
        }

    }

    public BaseResp(ServiceErrorCode errorCode) {
        this.returnCode = errorCode.getErrorCode();
        this.returnMsg = errorCode.getErrorMsg();
    }

    public BaseResp(String returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    /**
     * 获取返回码
     * 
     * @return returnCode 返回码
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * 设置返回码
     * 
     * @param returnCode 返回码
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * 获取返回信息
     * 
     * @return returnMsg 返回信息
     */
    public String getReturnMsg() {
        return returnMsg;
    }

    /**
     * 设置返回信息
     * 
     * @param returnMsg 返回信息
     */
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public boolean isSuccess() {
        if (StringUtils.isNotBlank(returnCode) && returnCode.length() >= 4) {
            return SysErrorCode.SUCCESS.getErrorCode().equals(returnCode.substring(returnCode.length() - 4, returnCode.length()));
        }
        return false;
    }
}
 