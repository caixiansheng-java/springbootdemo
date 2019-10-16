package com.hyx.demo.portal.dubbo.consumers;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hyx.demo.core.rs.service.PortalCoreDubboService;
import com.hyx.demo.sdk.vo.BaseResultResp;

/** 
 * @ClassName:CommonDubboConsumers <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年10月16日 上午11:43:50 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
@Service
public class CommonDubboConsumers {
	@Reference
	private PortalCoreDubboService portalCoreDubboService;
	
	public BaseResultResp<String> testDubbo(String jsonStr){
		return portalCoreDubboService.praseJson(jsonStr);
	}
}
 