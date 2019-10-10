package com.hyx.demo.task.mapper;

import com.hyx.demo.task.domain.TTaskSysJobLog;

public interface TTaskSysJobLogMapper {
    int deleteByPrimaryKey(Integer jobLogId);

    int insert(TTaskSysJobLog record);

    int insertSelective(TTaskSysJobLog record);

    TTaskSysJobLog selectByPrimaryKey(Integer jobLogId);

    int updateByPrimaryKeySelective(TTaskSysJobLog record);

    int updateByPrimaryKey(TTaskSysJobLog record);
}