package com.hyx.demo.sdk.aop;

import java.io.Serializable;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hyx.demo.sdk.utils.JacksonUtis;
import com.hyx.demo.sdk.utils.MDCUtil;


/**
 * WebLogAop
 *
 */
/***
 * @Aspect -- 作用是把当前类标识为一个切面供容器读取
 * @Pointcut -- (切入点):就是带有通知的连接点，在程序中主要体现为书写切入点表达式
 * @Before -- 标识一个前置增强方法，相当于BeforeAdvice的功能
 * @AfterReturning -- 后置增强，相当于AfterReturningAdvice，方法退出时执行
 * @AfterThrowing -- 异常抛出增强，相当于ThrowsAdvice
 * @After -- final增强，不管是抛出异常或者正常退出都会执行
 * @Around -- 环绕增强，相当于MethodInterceptor 
 */
@Aspect
@Component
public class WebLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    // 申明一个切点 里面是 execution表达式
    @Pointcut("execution(public * com.hyx..*.controller..*(..))")
    private void controllerAspect() {
    }

    // 请求method前打印内容
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        MDCUtil.init(null, request);
        // 打印请求内容
        Object[] args = joinPoint.getArgs();
        boolean isSerial = true;
        if (args != null && args.length > 0) {
            for (Object object : args) {
                if (!(object instanceof Serializable)) {
                    isSerial = false;
                }
            }
        }
        LOGGER.debug("请求地址:{},请求类方法:{},请求类方法参数:{}", request.getRequestURI(), joinPoint.getSignature(),
                isSerial ? JacksonUtis.object2Json(joinPoint.getArgs()) : Arrays.toString(joinPoint.getArgs()));
    }

    // 在方法执行完结后打印返回内容
    @AfterReturning(returning = "ret", pointcut = "controllerAspect()")
    public void methodAfterReturing(Object ret) {
        LOGGER.debug("耗时: {} ms,响应内容:{}", System.currentTimeMillis() - startTime.get(), JacksonUtis.object2Json(ret));
    }
}
