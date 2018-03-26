package com.dgit.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.MemberVO;
import com.dgit.domain.UserVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	private static final String namespace = "com.dgit.mapper.MemberMapper.";

	@Autowired
	private SqlSession session;
	

	@Override
	public MemberVO selectOne(int mno) throws Exception {
		return session.selectOne(namespace+"selectOne",mno);
	}

	@Override
	public List<MemberVO> selectList() throws Exception { 
		return session.selectList(namespace+"selectList");
	}

	@Override
	public int insert(MemberVO vo) throws Exception {
		return session.insert(namespace+"insert",vo);
	}

	@Override
	public int delete(int mno) throws Exception {
		return session.delete(namespace+"delete",mno);
	}

	@Override
	public int update(MemberVO vo) throws Exception {
		return session.update(namespace+"update",vo);
	}

}
