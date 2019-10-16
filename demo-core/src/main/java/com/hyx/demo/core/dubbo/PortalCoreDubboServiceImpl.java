package com.hyx.demo.core.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.hyx.demo.core.rs.service.PortalCoreDubboService;
import com.hyx.demo.sdk.vo.BaseResultResp;

/** 
 * @ClassName:PortalCoreDubboServiceImpl <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年10月15日 下午4:19:48 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
@Service
public class PortalCoreDubboServiceImpl implements PortalCoreDubboService{

	@Override
	public BaseResultResp<String> praseJson(String jsonStr) {
		return new BaseResultResp<String>();
	}

}
 