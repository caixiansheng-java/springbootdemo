package com.hyx.demo.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * @ClassName:TestController <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年9月23日 上午11:32:34 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
@RestController
@RequestMapping("/")
public class TestController {
	@RequestMapping("/test")
	public Object test() {
		return 12345;
	}
}
 