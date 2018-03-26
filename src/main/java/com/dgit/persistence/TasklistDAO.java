package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.TaskListVO;


public interface TasklistDAO {
	public TaskListVO selectOne(int tlno) throws Exception;
	public List<TaskListVO> selectList() throws Exception;
	public int insert(TaskListVO vo) throws Exception;
	public int delete(int tlno) throws Exception;
	public int update(TaskListVO vo) throws Exception;
	public int changeOrder(TaskListVO vo) throws Exception;
}
