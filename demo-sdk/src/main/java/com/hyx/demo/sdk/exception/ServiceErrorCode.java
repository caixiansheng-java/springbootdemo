package com.hyx.demo.sdk.exception;

/**
 * 错误码接口类
 * ServiceErrorCode
 *
 * @author huangyaxiong 
 * @version 1.0 2019年9月4日
 */
public interface ServiceErrorCode {

    /**
     * @return
     */
    String getErrorCode();

    /**
     * @return
     */
    String getErrorMsg();
    /**
     * 判断是否成功
     * @return
     */
    boolean isSucc();
}
