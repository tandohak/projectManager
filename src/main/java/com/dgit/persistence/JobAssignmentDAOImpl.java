package com.dgit.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.JobAssignmentVO;
@Repository
public class JobAssignmentDAOImpl implements JobAssignmentDAO {
	private static final String namespace = "com.dgit.mapper.JobAssignmentMapper.";

	@Autowired
	private SqlSession session;
	
	
	@Override
	public JobAssignmentVO selectOne(JobAssignmentVO vo) throws Exception {
		return session.selectOne(namespace+"selectOne",vo);
	}

	@Override
	public List<JobAssignmentVO> selectList() throws Exception { 
		return session.selectList(namespace+"selectList");
	}

	@Override
	public int insert(JobAssignmentVO vo) throws Exception {
		return session.insert(namespace+"insert",vo);
	}

	@Override
	public int delete(JobAssignmentVO vo) throws Exception {
		return session.delete(namespace+"delete",vo);
	}

	@Override
	public int update(JobAssignmentVO vo) throws Exception {
		return session.update(namespace+"update",vo);
	}

	@Override
	public List<JobAssignmentVO> selectTaskNo(int taskno) throws Exception {
		return session.selectList(namespace+"selectList",taskno);
	}

}
