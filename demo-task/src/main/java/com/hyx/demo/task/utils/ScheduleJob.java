package com.hyx.demo.task.utils;

import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hyx.demo.sdk.bean.SpringContextUtil;
import com.hyx.demo.sdk.utils.DateTimeUtils;
import com.hyx.demo.sdk.utils.IdUtils;
import com.hyx.demo.sdk.utils.MDCUtil;
import com.hyx.demo.task.constants.TaskConstants;
import com.hyx.demo.task.domain.TTaskSysJob;
import com.hyx.demo.task.domain.TTaskSysJobLog;
import com.hyx.demo.task.service.SysJobLogService;

/**
 * 定时任务处理
 * 继承QuarteJobBean
 * @author huangyaxiong
 *
 */
@DisallowConcurrentExecution
public class ScheduleJob extends QuartzJobBean {
    private static final Logger log = LoggerFactory.getLogger(ScheduleJob.class);

    private ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) SpringContextUtil.getBean("myThreadPoolTaskExecutor");

    private final static SysJobLogService jobLogService = (SysJobLogService) SpringContextUtil.getBean(SysJobLogService.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        TTaskSysJob job = new TTaskSysJob();
        BeanUtils.copyProperties(context.getMergedJobDataMap().get(TaskConstants.TASK_PROPERTIES), job);
        TTaskSysJobLog jobLog = new TTaskSysJobLog();
        jobLog.setJobName(job.getJobName());
        jobLog.setJobGroup(job.getJobGroup());
        jobLog.setMethodName(job.getMethodName());
        jobLog.setMethodParams(job.getMethodParams());
        jobLog.setCreDt(DateTimeUtils.getCurrDt());
        jobLog.setCreTm(DateTimeUtils.getCurrTm());
        jobLog.setJobId(job.getJobId());
        long startTime = System.currentTimeMillis();
        try {
            MDCUtil.init("", "");
            // 执行任务
            log.info("任务开始执行 - 名称：{} 方法：{}", job.getJobName(), job.getMethodName());
            ScheduleRunnable task = new ScheduleRunnable(job.getJobName(), job.getMethodName(), job.getMethodParams());
            Future<?> future = executor.submit(task);
            Object executorResp = future.get();
            log.info("任务开始执行 - 结果：{}",JSONObject.toJSONString(executorResp));
            long times = System.currentTimeMillis() - startTime;
            if (executorResp != null && executorResp instanceof JSONObject) {
            	JSONObject resp = (JSONObject) executorResp;
                // 任务状态 0：成功 1：失败
                jobLog.setStatus(TaskConstants.COMM_FAIL_CODE);
                jobLog.setJobMessage(job.getJobName() + " 总共耗时：" + times + "毫秒 执行结果:" + JSONObject.toJSONString(resp));
                if (resp.getString("code") != null && resp.getString("code").equals("0000")) {
                    jobLog.setStatus(TaskConstants.COMM_SUCC_CODE);
                    jobLog.setExceptionInfo(StringUtils.substring(JSON.toJSONString(resp), 0, 2000));
                }
                jobLog.setJobMessage(StringUtils.substring(jobLog.getJobMessage(), 0, 500));
            } else {
                // 任务状态 0：成功 1：失败
                jobLog.setStatus(TaskConstants.COMM_FAIL_CODE);
                jobLog.setJobMessage(job.getJobName() + " 总共耗时：" + times + "毫秒");
            }
            log.info("任务执行结束 - 名称：{} 耗时：{} 毫秒", job.getJobName(), times);
        } catch (Exception e) {
            log.info("任务执行失败 - 名称：{} 方法：{}", job.getJobName(), job.getMethodName());
            log.error("任务执行异常  - ：", e);
            long times = System.currentTimeMillis() - startTime;
            jobLog.setJobMessage(job.getJobName() + " 总共耗时：" + times + "毫秒");
            // 任务状态 0：成功 1：失败
            jobLog.setStatus(TaskConstants.COMM_FAIL_CODE);
            jobLog.setExceptionInfo(StringUtils.substring(e.getMessage(), 0, 2000));
        } finally {
            jobLogService.addJobLog(jobLog);
        }
    }
}
