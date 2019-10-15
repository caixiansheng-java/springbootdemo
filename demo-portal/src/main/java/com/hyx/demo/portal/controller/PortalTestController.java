package com.hyx.demo.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * @ClassName:PortalTestController <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年10月14日 下午2:59:45 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
@Controller
@RequestMapping("test")
public class PortalTestController {
	
	@RequestMapping(value="/test")
	public String test(){
		System.out.println("redirect to home page!");
		return "/test";
	}

	@RequestMapping(value="/home")
	public String home(){
		System.out.println("redirect to home page!");
		return "/index";
	}

}
 