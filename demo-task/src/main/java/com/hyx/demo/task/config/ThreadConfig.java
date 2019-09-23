package com.hyx.demo.task.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync //开启异步线程
public class ThreadConfig implements AsyncConfigurer {
	//核心线程数 线程池创建时候初始化的线程数
	@Value("${thread.corePoolSize}")
	public String corePoolSize;
	//最大线程数 线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
	@Value("${thread.maxPoolSize}")
	public String maxPoolSize;
	//缓冲队列 用来缓冲执行任务的队列
	@Value("${thread.queueCapacity}")
	public String queueCapacity;
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new MyThreadPoolTaskExecutor();
	    executor.setCorePoolSize(Integer.valueOf(corePoolSize));
	    executor.setMaxPoolSize(Integer.valueOf(maxPoolSize));
	    executor.setQueueCapacity(Integer.valueOf(queueCapacity));
	    // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
	    executor.initialize();
	    return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
