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

	@Override
	public MemberVO selectOneUnoAndwcode(MemberVO vo) throws Exception {
		return dao.selectOneUnoAndwcode(vo);
	}
	
	@Override 
	public List<MemberVO> searchMemberByName(String name,String wcode) throws Exception {
		return dao.searchMemberByName(name,wcode);
	}

	@Override
	public List<MemberVO> selectListByPno(int pno) throws Exception {
		return dao.selectListByPno(pno);
	}

	@Override
	public MemberVO selectOneByPnoAndUno(int uno, int pno) throws Exception {
		return dao.selectOneByPnoAndUno(uno, pno); 
	}

	@Override
	public List<MemberVO> selectListByPnoWithMemAssignment(int pno) throws Exception {
		return dao.selectListByPnoWithMemAssignment(pno);
	}

	@Override
	public List<MemberVO> selectListByTasknoWithMemAssignment(int taskno) throws Exception {
		return dao.selectListByTasknoWithMemAssignment(taskno);
	}

	@Override
	public List<MemberVO> searchByPnoWithMemAssignment(String name, int pno) throws Exception {
		return dao.searchByPnoWithMemAssignment(name, pno);
	}

	@Override
	public List<MemberVO> searchListByUnoJoinWorkspace(String name, int uno) {
		return dao.searchListByUnoJoinWorkspace(name, uno);
	}
	    
} 
  