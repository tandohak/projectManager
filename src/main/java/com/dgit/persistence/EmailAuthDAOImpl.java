package com.dgit.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.EmailAuthVO;
@Repository
public class EmailAuthDAOImpl implements EmailAuthDAO {
	private static final String namespace = "com.dgit.mapper.EmailAuthMapper.";

	@Autowired
	private SqlSession session;

	@Override
	public EmailAuthVO selectOne(EmailAuthVO vo) throws Exception {
		return session.selectOne(namespace+"selectOne",vo);
	}

	@Override
	public int insert(EmailAuthVO vo) throws Exception {
		return session.insert(namespace+"insert",vo);
	}

	@Override
	public int delete(EmailAuthVO vo) throws Exception {
		return session.delete(namespace+"delete",vo);
	}

}
