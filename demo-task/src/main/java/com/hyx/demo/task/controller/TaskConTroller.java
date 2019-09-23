package com.hyx.demo.task.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hyx.demo.sdk.utils.DateTimeUtils;
import com.hyx.demo.sdk.vo.BaseResp;
import com.hyx.demo.task.constants.TaskConstants;
import com.hyx.demo.task.domain.TTaskSysJob;
import com.hyx.demo.task.enums.TaskErrorCode;
import com.hyx.demo.task.service.SysJobService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *定时任务入口
 * 
 * @author huangyaxiong
 *
 */
@Api(value = " 定时任务入口", tags = " 定时任务入口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/task")
public class TaskConTroller {
	
	private Logger logger = LoggerFactory.getLogger(TaskConTroller.class);
	@Autowired
	private SysJobService sysJobService;
	
	@ApiOperation(value = "新增定时任务", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value = "/addJob", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BaseResp addJob(@RequestParam("cronExpression") String cronExpression,
			@RequestParam("jobGroup") String jobGroup,
			@RequestParam("jobName") String jobName,
			@RequestParam("methodName") String methodName,
			@RequestParam("methodParams") String methodParams,
			@RequestParam(name = "misFirePolicy" , defaultValue = TaskConstants.MISFIRE_DEFAULT) String misFirePolicy) {
		BaseResp resp = new BaseResp(TaskErrorCode.TASK_SUCCESS);
		try {
			TTaskSysJob sysJob = new TTaskSysJob();
			sysJob.setCreOpr("sys");
			sysJob.setJobName(jobName);
			sysJob.setMethodName(methodName);
			sysJob.setMethodParams(methodParams);
			sysJob.setCronExpression(cronExpression);
			sysJob.setJobGroup(jobGroup);
			sysJob.setMisFirePolicy(misFirePolicy);
			sysJob.setTmSmp(DateTimeUtils.getCurrDtTm());
			int result = sysJobService.insertJobCron(sysJob);
			if(result <= 0) {
				resp = new BaseResp(TaskErrorCode.TASK_FAIL);
				
			}
		} catch (Exception e) {
			resp = new BaseResp(TaskErrorCode.TASK_FAIL);
			logger.info("添加异常-{}",e);
		} finally {
			logger.info("操作返回结果-",JSON.toJSONString(resp));
		}
		return resp;
	}
	
	@ApiOperation(value = "修改定时任务", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value = "/updJob", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BaseResp updJob(@RequestParam(name = "cronExpression" ,required = false) String cronExpression,
			@RequestParam(name = "jobGroup"  ,required = false) String jobGroup,
			@RequestParam(name = "jobName"  ,required = false) String jobName,
			@RequestParam(name = "methodName" ,required = false) String methodName,
			@RequestParam(name = "methodParams" ,required = false) String methodParams,
			@RequestParam(name = "misFirePolicy"  ,required = false, defaultValue = TaskConstants.MISFIRE_DO_NOTHING) String misFirePolicy,
			@RequestParam(name = "status" ,required = false) String status,
			@RequestParam(name = "jobId") String jobId) {
		BaseResp resp = new BaseResp(TaskErrorCode.TASK_SUCCESS);
		try {
			TTaskSysJob sysJob = new TTaskSysJob();
			sysJob.setJobName(jobName);
			sysJob.setMethodName(methodName);
			sysJob.setMethodParams(methodParams);
			sysJob.setCronExpression(cronExpression);
			sysJob.setJobGroup(jobGroup);
			sysJob.setStatus(status);
			sysJob.setMisFirePolicy(misFirePolicy);
			sysJob.setTmSmp(DateTimeUtils.getCurrDtTm());
			resp = sysJobService.updJobCron(sysJob);
		} catch (Exception e) {
			resp = new BaseResp(TaskErrorCode.TASK_FAIL);
			logger.info("添加异常-{}",e);
		} finally {
			logger.info("操作返回结果-",JSON.toJSONString(resp));
		}
		return resp;
	}
}
