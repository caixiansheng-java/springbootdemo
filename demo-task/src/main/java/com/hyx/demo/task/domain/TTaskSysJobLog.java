package com.hyx.demo.task.domain;

public class TTaskSysJobLog {
    /**
     * 
     */
    private Integer jobLogId;

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
     * 
     */
    private String jobMessage;

    /**
     * 执行结果 1：成功 0 失败
     */
    private String status;

    /**
     * 
     */
    private String exceptionInfo;

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
     * @return JOB_LOG_ID 
     */
    public Integer getJobLogId() {
        return jobLogId;
    }

    /**
     * 
     * @param jobLogId 
     */
    public void setJobLogId(Integer jobLogId) {
        this.jobLogId = jobLogId;
    }

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
     * 
     * @return JOB_MESSAGE 
     */
    public String getJobMessage() {
        return jobMessage;
    }

    /**
     * 
     * @param jobMessage 
     */
    public void setJobMessage(String jobMessage) {
        this.jobMessage = jobMessage == null ? null : jobMessage.trim();
    }

    /**
     * 执行结果 1：成功 0 失败
     * @return STATUS 执行结果 1：成功 0 失败
     */
    public String getStatus() {
        return status;
    }

    /**
     * 执行结果 1：成功 0 失败
     * @param status 执行结果 1：成功 0 失败
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 
     * @return EXCEPTION_INFO 
     */
    public String getExceptionInfo() {
        return exceptionInfo;
    }

    /**
     * 
     * @param exceptionInfo 
     */
    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo == null ? null : exceptionInfo.trim();
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
}