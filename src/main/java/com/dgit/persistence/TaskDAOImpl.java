package com.dgit.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.TaskVO;

@Repository
public class TaskDAOImpl implements TaskDAO {
	private static final String namespace = "com.dgit.mapper.TaskMapper.";

	@Autowired
	private SqlSession session;

	@Override
	public TaskVO selectOne(int taskno) throws Exception {
		return session.selectOne(namespace + "selectOne", taskno);
	}

	@Override
	public List<TaskVO> selectList() throws Exception {
		return session.selectList(namespace + "selectList");
	}

	@Override
	public int insert(TaskVO vo) throws Exception {
		return session.insert(namespace + "insert", vo);
	}

	@Override
	public int delete(int taskno) throws Exception {
		return session.delete(namespace + "delete", taskno);
	}

	@Override
	public int update(TaskVO vo) throws Exception {
		return session.update(namespace + "update", vo);
	}

	@Override
	public List<TaskVO> selectListWithTlno(int tlno) throws Exception {
		return session.selectList(namespace + "selectWithTlno",tlno);
	}

	@Override
	public List<TaskVO> selectListByPno(int pno) throws Exception {
		return session.selectList(namespace + "selectListByPno", pno);
	}

	@Override
	public int deleteByTlno(int tlno) throws Exception {
		return session.delete(namespace + "deleteByTlno", tlno);
	}
 
} 
