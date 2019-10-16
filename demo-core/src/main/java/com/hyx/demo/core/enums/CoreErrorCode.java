package com.hyx.demo.core.enums;

import com.hyx.demo.sdk.exception.ServiceErrorCode;

/** 
 * @ClassName:CoreErrorCodes <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年10月15日 下午4:11:49 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
public enum CoreErrorCode implements ServiceErrorCode {
	SUCC("0000","SUCCESS"),
	FAIL("COR9999","系统错误"),
	;

	private CoreErrorCode(String errorCode,String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	private String errorCode;
	private String errorMsg;
	
	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getErrorMsg() {
		return errorMsg;
	}

	@Override
	public boolean isSucc() {
		return errorCode != null && errorCode.equals(SUCC.getErrorCode());
	}

}
 