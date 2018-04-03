package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgit.domain.MemberVO;
import com.dgit.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO dao;
	
	@Override
	public MemberVO selectOne(int mno) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectOne(mno);
	}

	@Override
	public List<MemberVO> selectList() throws Exception {
		return dao.selectList();
	}

	@Override
	public int insert(MemberVO vo) throws Exception {
		return dao.insert(vo);
	}

	@Override
	public int delete(int mno) throws Exception {
		return dao.delete(mno);
	}

	@Override
	public int update(MemberVO vo) throws Exception {
		return dao.update(vo);
	}

	@Override
	public List<MemberVO> selectListByWcode(String wcode) throws Exception {
		return dao.selectListByWcode(wcode);
	}

	@Override
	public List<MemberVO> selectListByUno(int uno) throws Exception {
		return dao.selectListByUno(uno);
	}
  
	@Override
	public List<MemberVO> selectListByUnoJoinWorkspace(int uno) throws Exception {
		return dao.selectListByUnoJoinWorkspace(uno);
	}
	
}
