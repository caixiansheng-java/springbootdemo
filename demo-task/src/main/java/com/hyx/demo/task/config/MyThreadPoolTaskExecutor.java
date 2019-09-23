package com.hyx.demo.task.config;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
@Component("myThreadPoolTaskExecutor")
public class MyThreadPoolTaskExecutor extends ThreadPoolTaskExecutor{

	private Logger logger = LoggerFactory.getLogger(MyThreadPoolTaskExecutor.class);
	public void showThreadWorkStatus(String method) {
		ThreadPoolExecutor threadPoolExecutor = super.getThreadPoolExecutor();
		if(threadPoolExecutor == null) {
			return;
		}
		logger.info("{}, {},taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
				this.getThreadNamePrefix(),
				method,
				threadPoolExecutor.getTaskCount(),
				threadPoolExecutor.getCompletedTaskCount(),
				threadPoolExecutor.getActiveCount(),
				threadPoolExecutor.getQueue().size());
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void execute(Runnable task) {
		showThreadWorkStatus("execute 1.0");
		super.execute(task);
	}

	@Override
	public void execute(Runnable task, long startTimeout) {
		showThreadWorkStatus("execute 2.0");
		super.execute(task, startTimeout);
	}

	@Override
	public Future<?> submit(Runnable task) {
		showThreadWorkStatus("submit 1.0");
		return super.submit(task);
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		showThreadWorkStatus("submit 2.0");
		return super.submit(task);
	}

	@Override
	public ListenableFuture<?> submitListenable(Runnable task) {
		// TODO Auto-generated method stub
		showThreadWorkStatus("submitListenable 1.0");
		return super.submitListenable(task);
	}

	@Override
	public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
		showThreadWorkStatus("submitListenable 2.0");
		return super.submitListenable(task);
	}
}
