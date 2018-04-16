package com.dgit.service;

import java.util.List;

import com.dgit.domain.TaskListVO;

public interface TaskListService {
	public TaskListVO selectOne(int tlno) throws Exception;
	public List<TaskListVO> selectList(int pno) throws Exception;
	public int insert(TaskListVO vo) throws Exception;
	public int delete(int tlno) throws Exception;
	public int update(TaskListVO vo) throws Exception;
	public int changeOrder(TaskListVO vo) throws Exception;

	public int countTaskAllByPno(int pno) throws Exception;
	public int countTaskFinishByPno(int pno) throws Exception;
}
  