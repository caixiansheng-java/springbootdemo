package com.hyx.demo.task.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hyx.demo.sdk.vo.BaseResp;
import com.hyx.demo.task.enums.TaskErrorCode;

/** 
 * @ClassName:TestTask <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年9月6日 下午3:11:13 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
@Service("testTask")
public class TestTask {
	private Logger logger = LoggerFactory.getLogger(TestTask.class);
	public BaseResp test() {
		logger.info("TestTask===test===执行开始");
		BaseResp resp = new BaseResp(TaskErrorCode.TASK_SUCCESS);
		logger.info("TestTask===test===执行结束");
		return resp;
	}	
	
	
	public BaseResp testParamOne(String name) {
		logger.info("TestTask===testParamOne===执行开始a=name-{}",name);
		BaseResp resp = new BaseResp(TaskErrorCode.TASK_SUCCESS);
		logger.info("TestTask===test===执行结束");
		logger.info("TestTask===testParamOne===执行结束a=name-{}",name);
		return resp;
	}	
}
 