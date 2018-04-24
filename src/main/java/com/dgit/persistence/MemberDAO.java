package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.MemberVO;
import com.dgit.domain.UserVO;

public interface MemberDAO {
	public MemberVO selectOne(int mno) throws Exception;
	public List<MemberVO> selectListByUno(int uno) throws Exception;
	public List<MemberVO> selectListByUnoJoinWorkspace(int uno) throws Exception;
	public MemberVO selectOneUnoAndwcode(MemberVO vo) throws Exception;
	public List<MemberVO> selectList() throws Exception;
	public int insert(MemberVO vo) throws Exception;
	public int delete(int mno) throws Exception;
	public int update(MemberVO vo) throws Exception;
	public List<MemberVO> searchMemberByName(String name,String wcode) throws Exception;
	public List<MemberVO> selectListByWcode(String wcode) throws Exception;
	public List<MemberVO> selectListByPno(int pno) throws Exception;
	public MemberVO selectOneByPnoAndUno(int uno, int pno) throws Exception;
	public List<MemberVO> selectListByPnoWithMemAssignment(int pno) throws Exception;
	public List<MemberVO> selectListByTasknoWithMemAssignment(int taskno) throws Exception;
	public List<MemberVO> searchByPnoWithMemAssignment(String name,int pno) throws Exception;
	public List<MemberVO> searchListByUnoJoinWorkspace(String name, int uno);
}
