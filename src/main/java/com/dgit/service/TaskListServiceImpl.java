package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgit.domain.TaskListVO;
import com.dgit.persistence.TasklistDAO;

@Service
public class TaskListServiceImpl implements TaskListService {
	@Autowired
	private TasklistDAO dao;
	
	@Override
	public TaskListVO selectOne(int tlno) throws Exception {
		return dao.selectOne(tlno);
	}

	@Override
	public List<TaskListVO> selectList(int pno) throws Exception {
		return dao.selectList(pno);
	}
 
	@Override
	public int insert(TaskListVO vo) throws Exception {
		return dao.insert(vo);
	}

	@Override
	public int delete(int tlno) throws Exception {
		return dao.delete(tlno);
	}

	@Override
	public int update(TaskListVO vo) throws Exception {
		return dao.update(vo); 
	}

	@Override
	public int changeOrder(TaskListVO vo) throws Exception {
		return dao.changeOrder(vo);
	}

	@Override
	public int countTaskAllByPno(int pno) throws Exception {
		return dao.countTaskAllByPno(pno);
	}

	@Override
	public int countTaskFinishByPno(int pno) throws Exception {
		return dao.countTaskFinishByPno(pno);
	} 

}
