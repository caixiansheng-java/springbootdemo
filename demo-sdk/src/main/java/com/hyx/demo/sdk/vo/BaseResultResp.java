package com.hyx.demo.sdk.vo;

import com.hyx.demo.sdk.exception.ServiceErrorCode;

/**
 * ResultResp
 *
 * @author liaochangxun 2019年6月3日 上午11:12:46
 */
public class BaseResultResp<T> extends BaseResp {

    private static final long serialVersionUID = 1L;

    /**
     * 数据对象
     */
    protected T data;
    
    /**
     * 
     */
    public BaseResultResp() {
        super();
    }

    /**
     * @param e
     */
    public BaseResultResp(Exception e) {
        super(e);
    }

    /**
     * @param errorCode
     */
    public BaseResultResp(ServiceErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * @param returnCode
     * @param returnMsg
     */
    public BaseResultResp(String returnCode, String returnMsg) {
        super(returnCode, returnMsg);
    }

    /**
     * 获取数据对象
     * 
     * @return data 数据对象
     */
    public T getData() {
        return data;
    }

    /**
     * 设置数据对象
     * 
     * @param data 数据对象
     */
    public void setData(T data) {
        this.data = data;
    }

}
