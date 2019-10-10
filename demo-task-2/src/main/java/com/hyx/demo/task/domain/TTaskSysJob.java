package com.hyx.demo.task.domain;

public class TTaskSysJob {
    /**
     * 
     */
    private Integer jobId;

    /**
     * 
     */
    private String jobName;

    /**
     * 
     */
    private String jobGroup;

    /**
     * 
     */
    private String methodName;

    /**
     * 
     */
    private String methodParams;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 计划执行错误策略（1立即执行 2执行一次 3放弃执行） 
     * 1:(以错过的第一个频率时间立刻开始执行,重做错过的所有频率周期后,当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行)
     * 2:(以当前时间为触发频率立刻触发一次执行,然后按照Cron频率依次执行)
     * 3:(不触发立即执行;等待下次Cron触发频率到达时刻开始按照Cron频率依次执行)
     */
    private String misFirePolicy;

    /**
     * 1：生效 0：暂停
     */
    private String status;

    /**
     * 
     */
    private String creOpr;

    /**
     * 
     */
    private String creDt;

    /**
     * 
     */
    private String creTm;

    /**
     * 
     */
    private String updOpr;

    /**
     * 
     */
    private String updDt;

    /**
     * 
     */
    private String updTm;

    /**
     * 
     */
    private String rmk;

    /**
     * 
     */
    private String tmSmp;

    /**
     * 
     * @return JOB_ID 
     */
    public Integer getJobId() {
        return jobId;
    }

    /**
     * 
     * @param jobId 
     */
    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    /**
     * 
     * @return JOB_NAME 
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 
     * @param jobName 
     */
    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    /**
     * 
     * @return JOB_GROUP 
     */
    public String getJobGroup() {
        return jobGroup;
    }

    /**
     * 
     * @param jobGroup 
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup == null ? null : jobGroup.trim();
    }

    /**
     * 
     * @return METHOD_NAME 
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 
     * @param methodName 
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    /**
     * 
     * @return METHOD_PARAMS 
     */
    public String getMethodParams() {
        return methodParams;
    }

    /**
     * 
     * @param methodParams 
     */
    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams == null ? null : methodParams.trim();
    }

    /**
     * cron表达式
     * @return CRON_EXPRESSION cron表达式
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * cron表达式
     * @param cronExpression cron表达式
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    /**
     * 计划执行错误策略（1立即执行 2执行一次 3放弃执行） 
     * 1:(以错过的第一个频率时间立刻开始执行,重做错过的所有频率周期后,当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行)
     * 2:(以当前时间为触发频率立刻触发一次执行,然后按照Cron频率依次执行)
     * 3:(不触发立即执行;等待下次Cron触发频率到达时刻开始按照Cron频率依次执行)
     * @return MIS_FIRE_POLICY 计划执行错误策略（1立即执行 2执行一次 3放弃执行） 
     * 1:(以错过的第一个频率时间立刻开始执行,重做错过的所有频率周期后,当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行)
     * 2:(以当前时间为触发频率立刻触发一次执行,然后按照Cron频率依次执行)
     * 3:(不触发立即执行;等待下次Cron触发频率到达时刻开始按照Cron频率依次执行)
     */
    public String getMisFirePolicy() {
        return misFirePolicy;
    }

    /**
     * 计划执行错误策略（1立即执行 2执行一次 3放弃执行） 
     * 1:(以错过的第一个频率时间立刻开始执行,重做错过的所有频率周期后,当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行)
     * 2:(以当前时间为触发频率立刻触发一次执行,然后按照Cron频率依次执行)
     * 3:(不触发立即执行;等待下次Cron触发频率到达时刻开始按照Cron频率依次执行)
     * @param misFirePolicy 计划执行错误策略（1立即执行 2执行一次 3放弃执行） 
     * 1:(以错过的第一个频率时间立刻开始执行,重做错过的所有频率周期后,当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行)
     * 2:(以当前时间为触发频率立刻触发一次执行,然后按照Cron频率依次执行)
     * 3:(不触发立即执行;等待下次Cron触发频率到达时刻开始按照Cron频率依次执行)
     */
    public void setMisFirePolicy(String misFirePolicy) {
        this.misFirePolicy = misFirePolicy == null ? null : misFirePolicy.trim();
    }

    /**
     * 1：生效 0：暂停
     * @return STATUS 1：生效 0：暂停
     */
    public String getStatus() {
        return status;
    }

    /**
     * 1：生效 0：暂停
     * @param status 1：生效 0：暂停
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 
     * @return CRE_OPR 
     */
    public String getCreOpr() {
        return creOpr;
    }

    /**
     * 
     * @param creOpr 
     */
    public void setCreOpr(String creOpr) {
        this.creOpr = creOpr == null ? null : creOpr.trim();
    }

    /**
     * 
     * @return CRE_DT 
     */
    public String getCreDt() {
        return creDt;
    }

    /**
     * 
     * @param creDt 
     */
    public void setCreDt(String creDt) {
        this.creDt = creDt == null ? null : creDt.trim();
    }

    /**
     * 
     * @return CRE_TM 
     */
    public String getCreTm() {
        return creTm;
    }

    /**
     * 
     * @param creTm 
     */
    public void setCreTm(String creTm) {
        this.creTm = creTm == null ? null : creTm.trim();
    }

    /**
     * 
     * @return UPD_OPR 
     */
    public String getUpdOpr() {
        return updOpr;
    }

    /**
     * 
     * @param updOpr 
     */
    public void setUpdOpr(String updOpr) {
        this.updOpr = updOpr == null ? null : updOpr.trim();
    }

    /**
     * 
     * @return UPD_DT 
     */
    public String getUpdDt() {
        return updDt;
    }

    /**
     * 
     * @param updDt 
     */
    public void setUpdDt(String updDt) {
        this.updDt = updDt == null ? null : updDt.trim();
    }

    /**
     * 
     * @return UPD_TM 
     */
    public String getUpdTm() {
        return updTm;
    }

    /**
     * 
     * @param updTm 
     */
    public void setUpdTm(String updTm) {
        this.updTm = updTm == null ? null : updTm.trim();
    }

    /**
     * 
     * @return RMK 
     */
    public String getRmk() {
        return rmk;
    }

    /**
     * 
     * @param rmk 
     */
    public void setRmk(String rmk) {
        this.rmk = rmk == null ? null : rmk.trim();
    }

    /**
     * 
     * @return TM_SMP 
     */
    public String getTmSmp() {
        return tmSmp;
    }

    /**
     * 
     * @param tmSmp 
     */
    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp == null ? null : tmSmp.trim();
    }
}