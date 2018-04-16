package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.TaskVO;


public interface TaskDAO {
	public TaskVO selectOne(int taskno) throws Exception;
	public List<TaskVO> selectList() throws Exception;
	public List<TaskVO> selectListWithTlno(int tlno) throws Exception;
	public int insert(TaskVO vo) throws Exception;
	public int delete(int taskno) throws Exception;
	public int update(TaskVO vo) throws Exception;
	public List<TaskVO> selectListByPno(int pno) throws Exception;
}
 