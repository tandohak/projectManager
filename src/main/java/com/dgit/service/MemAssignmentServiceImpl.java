package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgit.domain.MemAssignmentVO;
import com.dgit.persistence.MemAssignmentDAO;

@Service
public class MemAssignmentServiceImpl implements MemAssignmentService {
	
	@Autowired
	private MemAssignmentDAO dao;
	
	@Override
	public MemAssignmentVO selectOne(MemAssignmentVO vo) throws Exception {
		return dao.selectOne(vo);
	}

	@Override
	public List<MemAssignmentVO> selectList(int pno) throws Exception {
		return dao.selectList(pno);
	}

	@Override
	public int insert(MemAssignmentVO vo) throws Exception {
		MemAssignmentVO tempVO = dao.selectOne(vo);
		int res = -1;
		if(tempVO == null){
			res = dao.insert(vo);
		}    
		return res;  
	}
  
	@Override
	public int delete(MemAssignmentVO vo) throws Exception {
		return dao.delete(vo);
	}

	@Override
	public int update(MemAssignmentVO vo) throws Exception {
		return dao.update(vo);
	}

}
