package com.hyx.demo.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyx.demo.task.domain.TTaskSysJobLog;
import com.hyx.demo.task.mapper.TTaskSysJobLogMapper;


@Service
public class SysJobLogService {
	@Autowired
	private TTaskSysJobLogMapper tTaskSysJobLogMapper;

	public void addJobLog(TTaskSysJobLog jobLog) {
		tTaskSysJobLogMapper.insertSelective(jobLog);
	}

}
