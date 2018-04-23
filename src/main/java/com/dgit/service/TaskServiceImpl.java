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

	@Override
	public List<TaskVO> selectListByPno(int pno) throws Exception {
		return dao.selectListByPno(pno); 
	}

	@Override
	public int deleteByTlno(int tlno) throws Exception {
		return dao.deleteByTlno(tlno); 
	}

	@Override
	public int countFinishTaskByPno(int pno) throws Exception {
		return dao.countFinishTaskByPno(pno);
	}

	@Override
	public int countProgressingTaskByPno(int pno) throws Exception {
		return dao.countProgressingTaskByPno(pno);      
	}

	@Override
	public int countPassedTaskByPno(int pno) throws Exception {
		return dao.countPassedTaskByPno(pno);
	}

	@Override
	public int countPlannedTaskByPno(int pno) throws Exception {
		return dao.countPlannedTaskByPno(pno);
	}

	@Override
	public int countNoPlannendTaskByPno(int pno) throws Exception {
		return dao.countNoPlannendTaskByPno(pno);    
	}

	@Override
	public int makeMecountFinishTaskByPno(int pno, int massno) throws Exception {
		// TODO Auto-generated method stub
		return dao.makeMecountFinishTaskByPno(pno, massno);
	}

	@Override
	public int makeMecountProgressingTaskByPno(int pno, int massno) throws Exception {
		// TODO Auto-generated method stub
		return  dao.makeMecountProgressingTaskByPno(pno, massno);
	}

	@Override
	public int makeMecountPassedTaskByPno(int pno, int massno) throws Exception {
		return  dao.makeMecountPassedTaskByPno(pno, massno);
	}

	@Override
	public int makeMecountPlannedTaskByPno(int pno, int massno) throws Exception {
		return  dao.makeMecountPlannedTaskByPno(pno, massno);
	}

	@Override
	public int makeMecountNoPlannendTaskByPno(int pno, int massno) throws Exception {
		return  dao.makeMecountNoPlannendTaskByPno(pno, massno);
	}

	@Override
	public int assignmentCountFinishTaskByPnoAndMassno(int pno, int massno) throws Exception {
		return  dao.assignmentCountFinishTaskByPnoAndMassno(pno, massno);
	}   

	@Override
	public int assignmentCountPassedTaskByPnoAndMassno(int pno, int massno) throws Exception {
		return dao.assignmentCountPassedTaskByPnoAndMassno(pno, massno);
	}

	@Override
	public int assignmentCountPlannedTaskByPnoAndMassno(int pno, int massno) throws Exception {
		return dao.assignmentCountPlannedTaskByPnoAndMassno(pno, massno);
	}

	@Override
	public int assignmentCountNoPlannendTaskByPnoAndMassno(int pno, int massno) throws Exception {
		return dao.assignmentCountNoPlannendTaskByPnoAndMassno(pno, massno);
	}         
}  
