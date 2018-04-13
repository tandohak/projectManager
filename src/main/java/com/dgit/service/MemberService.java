package com.dgit.service;

import java.util.List;

import com.dgit.domain.MemberVO;

public interface MemberService {
	public MemberVO selectOne(int mno) throws Exception;
	public List<MemberVO> selectList() throws Exception;
	public List<MemberVO> selectListByUno(int uno) throws Exception;
	public List<MemberVO> selectListByUnoJoinWorkspace(int uno) throws Exception;
	public int insert(MemberVO vo) throws Exception;
	public int delete(int mno) throws Exception;
	public int update(MemberVO vo) throws Exception;
	public List<MemberVO> selectListByWcode(String wcode) throws Exception;
	public MemberVO selectOneUnoAndwcode(MemberVO vo) throws Exception;
	public List<MemberVO> searchMemberByName(String name,String wcode) throws Exception;
	public List<MemberVO> selectListByPno(int pno) throws Exception; 
} 
