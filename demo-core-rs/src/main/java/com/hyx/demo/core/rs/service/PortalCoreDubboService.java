package com.hyx.demo.core.rs.service;  
/** 
 * @ClassName:PortalCoreDubboService <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年10月15日 下午5:33:38 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */

import com.hyx.demo.sdk.vo.BaseResultResp;

public interface PortalCoreDubboService {
	public BaseResultResp<String> praseJson(String jsonStr);
}
 