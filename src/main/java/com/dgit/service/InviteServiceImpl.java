package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgit.domain.InviteVO;
import com.dgit.persistence.InviteDAO;
import com.dgit.persistence.UserDAO;

@Service
public class InviteServiceImpl implements InviteService {

	@Autowired
	private InviteDAO dao;
	
	@Override
	public List<InviteVO> selectListByWno(String wcode) throws Exception {
		return dao.selectListByWno(wcode);
	}

	@Override
	public int insert(InviteVO vo) throws Exception {
		return dao.insert(vo);
	}

	@Override
	public int delete(int ino) throws Exception {
		return dao.delete(ino);
	}

	@Override
	public int update(InviteVO vo) throws Exception {
		return dao.update(vo);
	}
	
}
