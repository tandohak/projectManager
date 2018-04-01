package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgit.domain.WorkspaceVO;
import com.dgit.persistence.WorkspaceDAO;
import com.dgit.util.TempKey;
@Service
public class WorkspaceServiceImpl implements WorkspaceService {

	@Autowired
	WorkspaceDAO dao;
	
	@Override
	public WorkspaceVO selectOne(String wcode) throws Exception {
		return dao.selectOne(wcode);
	}

	@Override
	public List<WorkspaceVO> selectList() throws Exception {
		return dao.selectList();
	}

	@Override
	public String insert(WorkspaceVO vo) throws Exception {
		String key = new TempKey().getKey(50, false);
		
		key = duplicateKey(key);
		
		vo.setWcode(key);
		
		int res = dao.insert(vo);
		
		if(res < 0){
			return "fail";
		}
		
		return key;
	}

	@Override
	public int delete(String wcode) throws Exception {
		return dao.delete(wcode);
	}
	
	private String duplicateKey(String key) throws Exception{
		WorkspaceVO voTemp  = selectOne(key);
		
		if(voTemp !=null){
			String tempKey = new TempKey().getKey(50, false);
			return duplicateKey(tempKey);
		}
		
		return key;
	}
}
