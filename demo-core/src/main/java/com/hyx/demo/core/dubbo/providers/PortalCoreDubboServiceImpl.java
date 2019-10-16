package com.hyx.demo.core.dubbo.providers;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.hyx.demo.core.rs.service.PortalCoreDubboService;
import com.hyx.demo.sdk.vo.BaseResultResp;

/** 
 * @ClassName:PortalCoreDubboServiceImpl <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年10月15日 下午4:19:48 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
@Component
@Service(timeout = 10000,interfaceClass = PortalCoreDubboService.class)
public class PortalCoreDubboServiceImpl implements PortalCoreDubboService{

	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(new BaseResultResp<String>()));
	}
	@Override
	public BaseResultResp<String> praseJson(String jsonStr) {
		return new BaseResultResp<String>();
	}

}
 