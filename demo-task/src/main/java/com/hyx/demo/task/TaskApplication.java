package com.hyx.demo.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/** 
 * @ClassName:TaskApplication <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年9月5日 下午4:57:42 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.hyx.demo.task.mapper")
@ComponentScan(basePackages={"com.hyx.demo"})
@PropertySource("classpath:dubbo-${spring.profiles.active}.properties")
@ImportResource({"classpath:spring-dubbo.xml"})
public class TaskApplication {
	public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
        System.out.println("==========TaskApplication启动成功===========");
    }
}
 