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
	public List<InviteVO> selectListByWno(String wcode) throws Exception {
		return session.selectList(namespace+"selectListByWno",wcode);
	}
	
	@Override
	public int insert(InviteVO vo) throws Exception {
		return session.insert(namespace+"insert",vo);
	}

	@Override
	public int delete(String invitee) throws Exception {
		return session.delete(namespace+"delete",invitee);
	}

	@Override
	public int update(InviteVO vo) throws Exception {
		return session.update(namespace+"update",vo);
	}

	@Override
	public List<InviteVO> selectListBywcodeAndInvitee(InviteVO vo) throws Exception {
		return session.selectList(namespace+"selectListBywcodeAndInvitee",vo);
	}

	@Override
	public List<InviteVO> selectListBywcode(String wcode) throws Exception {
		return session.selectList(namespace+"selectListBywcode",wcode);
	}

}
