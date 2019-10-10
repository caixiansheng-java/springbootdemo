package com.hyx.demo.task.mapper;

import java.util.List;

import com.hyx.demo.task.domain.TTaskSysJob;

public interface TTaskSysJobMapper {
    int deleteByPrimaryKey(Integer jobId);

    int insert(TTaskSysJob record);

    int insertSelective(TTaskSysJob record);

    TTaskSysJob selectByPrimaryKey(Integer jobId);

    int updateByPrimaryKeySelective(TTaskSysJob record);

    int updateByPrimaryKey(TTaskSysJob record);

	List<TTaskSysJob> listAll();
}