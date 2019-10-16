package com.hyx.demo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyx.demo.portal.dubbo.consumers.CommonDubboConsumers;
import com.hyx.demo.sdk.vo.BaseResultResp;

import io.swagger.annotations.Authorization;

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
	
	@Autowired
	private CommonDubboConsumers consumers;
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

	@RequestMapping(value="/testDubbo")
	@ResponseBody
	public BaseResultResp<String> testDubbo(){
		return consumers.testDubbo("12345");
	}

}
 