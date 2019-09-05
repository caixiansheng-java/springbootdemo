package com.hyx.demo.sdk.utils;

import java.util.UUID;

/** 
 * @ClassName:IdUtils <br/> 
 * @Reason:   TODO ADD REASON. <br/> 
 * @Date:     2019年9月4日 下午5:38:42 <br/> 
 * @author   huangyaxiong 
 * Copyright (c) 2019, hyx_java2012@163.com All Rights Reserved.       
 */
public class IdUtils {
	private static IdWorker ID = new IdWorker(1);

    /**
     * 生成随机ID
     * 
     * @return
     */
    public static long getNextId() {
        return ID.nextId();
    }

    /**
     * 生成随机ID
     * 
     * @param wordId
     * @return
     */
    public static long getNextId(int wordId) {
        ID = new IdWorker(wordId);
        return ID.nextId();
    }

    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
 