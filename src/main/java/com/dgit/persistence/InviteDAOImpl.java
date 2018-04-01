package com.dgit.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.InviteVO;
@Repository
public class InviteDAOImpl implements InviteDAO {
	
	private static final String namespace = "com.dgit.mapper.InviteMapper.";

	@Autowired
	private SqlSession session;

	@Override
	public List<InviteVO> selectListByWno(int wno) throws Exception {
		return session.selectList(namespace+"selectListByWno",wno);
	}
	
	@Override
	public int insert(InviteVO vo) throws Exception {
		return session.insert(namespace+"insert",vo);
	}

	@Override
	public int delete(int ino) throws Exception {
		return session.delete(namespace+"delete",ino);
	}

	@Override
	public int update(InviteVO vo) throws Exception {
		return session.update(namespace+"update",vo);
	}

}
