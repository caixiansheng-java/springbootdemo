package com.hyx.demo.task.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyx.demo.sdk.utils.DateTimeUtils;
import com.hyx.demo.sdk.vo.BaseResp;
import com.hyx.demo.task.constants.TaskConstants;
import com.hyx.demo.task.domain.TTaskSysJob;
import com.hyx.demo.task.enums.TaskErrorCode;
import com.hyx.demo.task.mapper.TTaskSysJobMapper;
import com.hyx.demo.task.utils.ScheduleUtils;

@Service
public class SysJobService {
	private Logger logger = LoggerFactory.getLogger(SysJobService.class);
	
	@Autowired
	private TTaskSysJobMapper  tTaskSysJobMapper;
	@Autowired
	private Scheduler scheduler;
	
	@PostConstruct
	public void initJobs() {
		List<TTaskSysJob> jobList = tTaskSysJobMapper.listAll();
		if(jobList == null || jobList.size() == 0) {
			logger.info("==============没有定时任务需要执行==============");
			return;
		}
		for(TTaskSysJob sysJob:jobList) {
			try {
				CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, sysJob.getJobId());
	            // 如果不存在，则创建
	            if (cronTrigger == null)
	            {
	                ScheduleUtils.createScheduleJob(scheduler, sysJob);
	            }
	            else
	            {
	                ScheduleUtils.updateScheduleJob(scheduler, sysJob);
	            }
			} catch (Exception e) {
				logger.info("定时任务jobId:{},处理异常a{}",sysJob.getJobId(),e);
			}
		}
	}
	
	 /**
     * 新增任务
     * 
     * @param job 调度信息 调度信息
     */
    public int insertJobCron(TTaskSysJob job){
        job.setStatus(TaskConstants.Status.PAUSE.getValue());
        job.setCreDt(DateTimeUtils.getCurrDt());
        job.setCreTm(DateTimeUtils.getCurrTm());
        job.setCreOpr("sys");
        job.setTmSmp(DateTimeUtils.getCurrDtTm());
        int rows = tTaskSysJobMapper.insertSelective(job);
        if (rows > 0)
        {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
        return rows;
    }
    /**
     * 修改任务调度信息
     * @param sysJob
     * @return
     */
	public BaseResp updJobCron(TTaskSysJob sysJob) {
		BaseResp resp = new BaseResp();
		try {
			TTaskSysJob temp = tTaskSysJobMapper.selectByPrimaryKey(sysJob.getJobId());
			if(temp == null) {
				resp =new BaseResp(TaskErrorCode.ERROR_JOB_ID);
				return resp;
			}
			sysJob.setUpdDt(DateTimeUtils.getCurrDt());
			sysJob.setUpdTm(DateTimeUtils.getCurrTm());
			sysJob.setUpdOpr("sys");
			sysJob.setTmSmp(DateTimeUtils.getCurrDtTm());
			int rows = tTaskSysJobMapper.updateByPrimaryKeySelective(sysJob);
			if(rows > 0) {
				temp = tTaskSysJobMapper.selectByPrimaryKey(sysJob.getJobId());
				//更新定时任务
				ScheduleUtils.updateScheduleJob(scheduler, temp);
			}else {
				logger.info("修改失败");
				resp =new BaseResp(TaskErrorCode.ERROR_UPDATE);
				return resp;
			}
		} catch (Exception e) {
			logger.info("修改失败-{}",e);
			resp =new BaseResp(TaskErrorCode.ERROR_UPDATE);
			return resp;
		}
		return resp;
	}
}
