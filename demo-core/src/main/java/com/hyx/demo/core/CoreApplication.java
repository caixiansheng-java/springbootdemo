package com.hyx.demo.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** 
 * @ClassName:CoreApplication <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年10月15日 下午4:10:44 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
@SpringBootApplication
@EnableSwagger2 // Swagger的开关，表示我们在项目中启用Swagger
@ComponentScan(basePackages={"com.hyx.demo"})
@EnableDubbo
@EnableCaching 
public class CoreApplication {
	private static Logger logger = LoggerFactory.getLogger(CoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
		logger.info("======================CoreApplication启动成功===========================");
	}
}
 