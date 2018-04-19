package com.dgit.service;

import java.util.List;

import com.dgit.domain.TaskVO;

public interface TaskService {
	public TaskVO selectOne(int taskno) throws Exception;
	public List<TaskVO> selectList() throws Exception;
	public List<TaskVO> selectListWithTlno(int tlno) throws Exception;
	public int insert(TaskVO vo) throws Exception;
	public int delete(int taskno) throws Exception;
	public int update(TaskVO vo) throws Exception;
	public int deleteByTlno(int tlno) throws Exception;
	public List<TaskVO> selectListByPno(int pno) throws Exception;
}
