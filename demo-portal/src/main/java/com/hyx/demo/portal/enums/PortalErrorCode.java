package com.hyx.demo.portal.enums;

import com.hyx.demo.sdk.exception.ServiceErrorCode;

/** 
 * @ClassName:PortalErrorCodes <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年10月14日 下午3:08:39 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
public enum PortalErrorCode implements ServiceErrorCode{
	SUCC("0000","交易成功"),
	DATA_IS_EMPTY("POR1001","数据为空"),
	FAIL("POR9999","交易失败"),;
	
	private String errorCode;
	private String errorMsg;
	private PortalErrorCode(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
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
	@Override
	public boolean isSucc() {
		return errorCode != null && errorCode.endsWith(SUCC.getErrorCode());
	}
}
 