package com.dgit.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dgit.domain.MemAssignmentVO;

@Repository
public class MemAssignmentDAOImpl implements MemAssignmentDAO {
	private static final String namespace = "com.dgit.mapper.MemAssignmentMapper.";

	@Autowired
	private SqlSession session;
	 
	
	@Override
	public MemAssignmentVO selectOne(MemAssignmentVO vo) throws Exception {
		return session.selectOne(namespace+"selectOne",vo);
	}

	@Override
	public List<MemAssignmentVO> selectList(int pno) throws Exception { 
		return session.selectList(namespace+"selectList",pno);
	}

	@Override
	public int insert(MemAssignmentVO vo) throws Exception {
		return session.insert(namespace+"insert",vo);
	}

	@Override
	public int delete(MemAssignmentVO vo) throws Exception {
		return session.delete(namespace+"delete",vo);
	}

	@Override
	public int update(MemAssignmentVO vo) throws Exception {
		return session.update(namespace+"update",vo);
	}

	@Override
	public List<MemAssignmentVO> selectListByTaskno(int taskno) throws Exception {
		return session.selectList(namespace+"selectListByTaskno",taskno);
	}  

}
