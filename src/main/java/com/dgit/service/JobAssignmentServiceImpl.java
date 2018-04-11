package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgit.domain.JobAssignmentVO;
import com.dgit.persistence.JobAssignmentDAO;

@Service
public class JobAssignmentServiceImpl implements JobAssignmentService {
	
	@Autowired
	private JobAssignmentDAO dao;
	
	@Override
	public JobAssignmentVO selectOne(JobAssignmentVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectOne(vo);
	}

	@Override
	public List<JobAssignmentVO> selectList() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public List<JobAssignmentVO> selectTaskNo(int taskno) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectTaskNo(taskno);
	}

	@Override
	public int insert(JobAssignmentVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.insert(vo);
	}

	@Override
	public int delete(JobAssignmentVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.delete(vo);
	}

	@Override
	public int update(JobAssignmentVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.update(vo);
	}

}
