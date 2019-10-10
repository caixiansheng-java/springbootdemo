package com.hyx.demo.task.enums;

import org.apache.commons.lang3.StringUtils;

import com.hyx.demo.sdk.exception.ServiceErrorCode;

/** 
 * @ClassName:TaskErrorCodes <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年9月6日 下午3:47:38 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
public enum TaskErrorCode implements ServiceErrorCode{
	
	TASK_SUCCESS("TSK0000","操作成功"),
	ERROR_POLICY("TSK0001","无效的定时策略"),
	ERROR_JOB_ID("TSK0002","无效的JOBID"), 
	ERROR_UPDATE("TSK0003","更新失败"),
	TASK_FAIL("TSK9999","系统错误"), 
	;
	
	private TaskErrorCode(String errorCode, String errorMsg) {
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
	
	@Override
	public boolean isSucc() {
		if(StringUtils.isEmpty(errorCode)) {
			return false;
		}
		if(errorCode.equals(TASK_SUCCESS.getErrorCode())) {
			return true;
		}
		return false;
	}

}
 