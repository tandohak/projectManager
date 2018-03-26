package com.dgit.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.ProjectVO;
@Repository
public class ProjectDAOImpl implements ProjectDAO {
	private static final String namespace = "com.dgit.mapper.ProjectMapper.";

	@Autowired
	private SqlSession session;

	@Override
	public ProjectVO selectOne(int pno) throws Exception {
		return session.selectOne(namespace + "selectOne", pno);
	}

	@Override
	public List<ProjectVO> selectList() throws Exception {
		return session.selectList(namespace + "selectList");
	}

	@Override
	public int insert(ProjectVO vo) throws Exception {
		return session.insert(namespace + "insert", vo);
	}

	@Override
	public int delete(int pno) throws Exception {
		return session.delete(namespace + "delete", pno);
	}

	@Override
	public int update(ProjectVO vo) throws Exception {
		return session.update(namespace + "update", vo);
	}
}
