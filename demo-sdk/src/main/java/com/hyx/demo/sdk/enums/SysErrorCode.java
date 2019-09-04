package com.hyx.demo.sdk.enums;

import com.hyx.demo.sdk.exception.ServiceErrorCode;

/** 
 * @ClassName:SysErrorCode <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年9月4日 上午11:40:10 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
public enum SysErrorCode implements ServiceErrorCode{
	SYS_SUCC("0000","操作成功"),
	SYS_FAIL("9999","系统错误"),
	;
	
	private SysErrorCode(String errorCode,String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	private String errorCode;
	private String errorMsg;
	
	@Override
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return errorCode;
	}

	@Override
	public String getErrorMsg() {
		// TODO Auto-generated method stub
		return errorMsg;
	}

}
 