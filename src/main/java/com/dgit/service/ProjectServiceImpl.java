package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgit.domain.MemAssignmentVO;
import com.dgit.domain.ProjectVO;
import com.dgit.persistence.MemAssignmentDAO;
import com.dgit.persistence.ProjectDAO;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectDAO dao;
	
	@Autowired
	private MemAssignmentDAO memAssDao;
	
	@Override
	public ProjectVO selectOne(int pno) throws Exception {
		return dao.selectOne(pno);
	}

	@Override
	public List<ProjectVO> selectList() throws Exception {
		return dao.selectList();
	} 

	@Override
	@Transactional
	public int insert(ProjectVO vo,List<Integer> memList,int makerMno) throws Exception {
		int res =  dao.insert(vo);
		 
		if(memList!=null){
			for(int mno : memList){
				MemAssignmentVO memAssVO = new MemAssignmentVO();
				memAssVO.setMno(mno);
				memAssVO.setPno(vo.getPno());
				if(makerMno==mno){
					memAssVO.setGrade(99);
				}else{
					memAssVO.setGrade(1);
				}
				memAssDao.insert(memAssVO);
			}
		}
		
		return res;
	}

	@Override
	public int delete(int pno) throws Exception {
		return dao.delete(pno);
	}

	@Override
	public int update(ProjectVO vo) throws Exception {
		return dao.update(vo);
	} 

	@Override
	public List<ProjectVO> selectListByWcode(String wcode) throws Exception {
		return dao.selectListByWcode(wcode);
	}

}
