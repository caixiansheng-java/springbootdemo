package com.hyx.demo.task.utils;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.hyx.demo.sdk.bean.SpringContextUtil;

/**
 * 执行定时任务
 * 
 * @author huangyaxiong
 *
 */
public class ScheduleRunnable implements Callable<Object> {
    private static final Logger log = LoggerFactory.getLogger(ScheduleRunnable.class);

    private Object target;
    private Method method;
    private String params;

    public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
        this.target = SpringContextUtil.getBean(beanName);
        this.params = params;

        if (StringUtils.isNotBlank(params)) {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        } else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

//    @Override
//    public void run() {
//        try {
//            ReflectionUtils.makeAccessible(method);
//            if (StringUtils.isNotEmpty(params)) {
//                method.invoke(target, params);
//            } else {
//                method.invoke(target);
//            }
//        } catch (Exception e) {
//            log.error("执行定时任务  - ：", e);
//        }
//    }

    @Override
    public Object call() throws Exception {
        Object obj = null;
        try {
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotEmpty(params)) {
                obj = method.invoke(target, params);
            } else {
                obj = method.invoke(target);
            }
        } catch (Exception e) {
            log.error("执行定时任务  - ：", e);
        }
        return obj;
    }
}
