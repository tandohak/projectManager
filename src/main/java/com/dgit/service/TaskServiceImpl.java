package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgit.domain.TaskVO;
import com.dgit.persistence.TaskDAO;
@Service 
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskDAO dao;
	
	@Override
	public TaskVO selectOne(int taskno) throws Exception {
		return dao.selectOne(taskno);
	}

	@Override
	public List<TaskVO> selectList() throws Exception {
		return dao.selectList();
	}

	@Override
	public List<TaskVO> selectListWithTlno(int tlno) throws Exception {
		return dao.selectListWithTlno(tlno);
	}

	@Override
	public int insert(TaskVO vo) throws Exception {
		return dao.insert(vo);
	}

	@Override
	public int delete(int taskno) throws Exception {
		return dao.delete(taskno);
	}

	@Override
	public int update(TaskVO vo) throws Exception {
		return dao.update(vo);
	}
}
