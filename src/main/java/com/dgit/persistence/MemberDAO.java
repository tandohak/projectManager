package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.MemberVO;
import com.dgit.domain.UserVO;

public interface MemberDAO {
	public MemberVO selectOne(int mno) throws Exception;
	public List<MemberVO> selectList() throws Exception;
	public int insert(MemberVO vo) throws Exception;
	public int delete(int mno) throws Exception;
	public int update(MemberVO vo) throws Exception;
}
