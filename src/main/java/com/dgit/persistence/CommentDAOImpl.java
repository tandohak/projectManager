package com.dgit.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.dgit.domain.CommentVO;

public class CommentDAOImpl implements CommentDAO {
	
	private static final String namespace = "com.dgit.mapper.CommentMapper.";
	
	@Autowired
	private SqlSession session;
	
	
	@Override
	public CommentVO selectOne(int cno) throws Exception {
		return session.selectOne(namespace+"selectOne",cno);
	}

	@Override
	public List<CommentVO> selectList() throws Exception { 
		return session.selectList(namespace+"selectList");
	}

	@Override
	public int insert(CommentVO vo) throws Exception {
		return session.insert(namespace+"insert",vo);
	}

	@Override
	public int delete(int cno) throws Exception {
		return session.delete(namespace+"delete",cno);
	}

	@Override
	public int update(CommentVO vo) throws Exception {
		return session.update(namespace+"update",vo);
	}

}
