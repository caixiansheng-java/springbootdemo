package com.hyx.demo.sdk.exception;

/**
 * ServiceException 异常类
 * 
 * @author huangyaxiong 2019年9月4日 下午4:26:00
 */
public class ServiceException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;

    public ServiceException() {
    }

    public ServiceException(String message) {
        setMessage(message);
    }

    public ServiceException(String code, String message) {
        this.code = code;
        setMessage(message);
    }

    public ServiceException(ServiceErrorCode errorCode) {
        this.setCode(errorCode.getErrorCode());
        this.setMessage(errorCode.getErrorMsg());
    }

    public ServiceException(Exception e, String code, String message) {
        if (e instanceof ServiceException) {
            ServiceException e1 = (ServiceException) e;
            this.setCode(e1.getCode());
            this.setMessage(e1.getMessage());
        } else {
            this.setCode(code);
            this.setMessage(message);
        }
    }

    /**
     * 获取错误码
     * 
     * @return code 错误码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置错误码
     * 
     * @param code 错误码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取错误信息
     * 
     * @return message 错误信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置错误信息
     * 
     * @param message 错误信息
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
