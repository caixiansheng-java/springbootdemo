package com.hyx.demo.portal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
/** 
 * @ClassName:PortalApplication <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年10月14日 下午2:51:17 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
@SpringBootApplication
@EnableSwagger2 // Swagger的开关，表示我们在项目中启用Swagger
@ComponentScan(basePackages={"com.hyx.demo"})
public class PortalApplication {
	private static Logger logger = LoggerFactory.getLogger(PortalApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
		logger.info("========================PortalApplication启动成功==========================");
	}
}
 