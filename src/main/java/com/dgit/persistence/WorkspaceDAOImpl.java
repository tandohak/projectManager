package com.dgit.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.WorkspaceVO;
@Repository
public class WorkspaceDAOImpl implements WorkspaceDAO {
	private static final String namespace = "com.dgit.mapper.WorkspaceMapper.";

	@Autowired
	private SqlSession session;

	@Override
	public WorkspaceVO selectOne(String wcode) throws Exception {
		return session.selectOne(namespace+"selectOne",wcode);
	}

	@Override
	public List<WorkspaceVO> selectList() throws Exception {
		return session.selectList(namespace+"selectList");
	}

	@Override
	public int insert(WorkspaceVO vo) throws Exception {
		return session.insert(namespace+"insert",vo);
	}

	@Override
	public int delete(String wcode) throws Exception {
		return session.delete(namespace+"delete",wcode);
	}

	@Override
	public int update(WorkspaceVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.update(namespace+"update",vo);
	}

	@Override
	public List<WorkspaceVO> selectListByUno(int uno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace+"selectListByUno",uno);
	}


}
