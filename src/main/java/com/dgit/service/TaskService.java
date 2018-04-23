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
	public int countFinishTaskByPno(int pno)  throws Exception;
	public int countProgressingTaskByPno(int pno)  throws Exception;
	public int countPassedTaskByPno(int pno)  throws Exception;;
	public int countPlannedTaskByPno(int pno)  throws Exception;;
	public int countNoPlannendTaskByPno(int pno)  throws Exception;
	
	public int makeMecountFinishTaskByPno(int pno,int massno)  throws Exception;
	public int makeMecountProgressingTaskByPno(int pno,int massno)  throws Exception;
	public int makeMecountPassedTaskByPno(int pno,int massno)  throws Exception;
	public int makeMecountPlannedTaskByPno(int pno,int massno)  throws Exception;
	public int makeMecountNoPlannendTaskByPno(int pno,int massno)  throws Exception;

	public int assignmentCountFinishTaskByPnoAndMassno(int pno,int massno)  throws Exception;
	public int assignmentCountPassedTaskByPnoAndMassno(int pno,int massno)  throws Exception;
	public int assignmentCountPlannedTaskByPnoAndMassno(int pno,int massno)  throws Exception;
	public int assignmentCountNoPlannendTaskByPnoAndMassno(int pno,int massno)  throws Exception;
}
