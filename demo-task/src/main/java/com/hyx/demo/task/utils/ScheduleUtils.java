package com.hyx.demo.task.utils;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hyx.demo.sdk.exception.ServiceException;
import com.hyx.demo.task.constants.TaskConstants;
import com.hyx.demo.task.domain.TTaskSysJob;
import com.hyx.demo.task.enums.TaskErrorCode;


public class ScheduleUtils {
	private static Logger log = LoggerFactory.getLogger(ScheduleUtils.class);
	
	/**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(Integer jobId) {
        return TriggerKey.triggerKey(TaskConstants.TASK_CLASS_NAME + jobId);
    }

    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(Integer jobId) {
        return JobKey.jobKey(TaskConstants.TASK_CLASS_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Integer jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            log.error("getCronTrigger 异常：", e);
        }
        return null;
    }
    
	 /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, TTaskSysJob job) {
        try {
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(job.getJobId())).build();
            
            // 表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            
            cronScheduleBuilder = handleCronScheduleMisfirePolicy(job, cronScheduleBuilder);
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(job.getJobId())).withSchedule(cronScheduleBuilder)
                    .build();

            // 放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(TaskConstants.TASK_PROPERTIES, job);

            scheduler.scheduleJob(jobDetail, trigger);

            // 暂停任务
            if (job.getStatus().equals(TaskConstants.Status.PAUSE.getValue())) {
                pauseJob(scheduler, job.getJobId());
            }
        } catch (SchedulerException e) {
            log.error("createScheduleJob 异常：", e);
        } catch (ServiceException e) {
            log.error("createScheduleJob 异常：", e);
        }
    }

    /**
     * 更新定时任务
     */
    public static void updateScheduleJob(Scheduler scheduler, TTaskSysJob job) {
        try {
            TriggerKey triggerKey = getTriggerKey(job.getJobId());

            // 表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            cronScheduleBuilder = handleCronScheduleMisfirePolicy(job, cronScheduleBuilder);

            CronTrigger trigger = getCronTrigger(scheduler, job.getJobId());

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();

            // 参数
            trigger.getJobDataMap().put(TaskConstants.TASK_PROPERTIES, job);

            scheduler.rescheduleJob(triggerKey, trigger);

            // 暂停任务
            if (job.getStatus().equals(TaskConstants.Status.PAUSE.getValue())) {
                pauseJob(scheduler, job.getJobId());
            }

        } catch (SchedulerException e) {
            log.error("SchedulerException 异常：", e);
        } catch (ServiceException e) {
            log.error("ServiceException 异常：", e);
        }
    }
    
    
    public static CronScheduleBuilder handleCronScheduleMisfirePolicy(TTaskSysJob job, CronScheduleBuilder cb) throws ServiceException {
        switch (job.getMisFirePolicy()) {
        case TaskConstants.MISFIRE_DEFAULT:
            return cb;
        case TaskConstants.MISFIRE_IGNORE_MISFIRES:
            // ——以错过的第一个频率时间立刻开始执行
            // ——重做错过的所有频率周期后
            // ——当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行
            return cb.withMisfireHandlingInstructionIgnoreMisfires();
        case TaskConstants.MISFIRE_FIRE_AND_PROCEED:
            // ——以当前时间为触发频率立刻触发一次执行
            // ——然后按照Cron频率依次执行
            return cb.withMisfireHandlingInstructionFireAndProceed();
        case TaskConstants.MISFIRE_DO_NOTHING:
            // ——不触发立即执行
            // ——等待下次Cron触发频率到达时刻开始按照Cron频率依次执行
            return cb.withMisfireHandlingInstructionDoNothing();
        default:
        	throw new ServiceException(TaskErrorCode.ERROR_POLICY.getErrorCode(), TaskErrorCode.ERROR_POLICY.getErrorMsg());
        }
    }
    
    /**
     * 暂停任务
     */
    public static void pauseJob(Scheduler scheduler, Integer jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("pauseJob 异常：", e);
        }
    }

    /**
     * 恢复任务
     */
    public static void resumeJob(Scheduler scheduler, Integer jobId) {
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("resumeJob 异常：", e);
        }
    }

    /**
     * 删除定时任务
     */
    public static void deleteScheduleJob(Scheduler scheduler, Integer jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("deleteScheduleJob 异常：", e);
        }
    }


}
