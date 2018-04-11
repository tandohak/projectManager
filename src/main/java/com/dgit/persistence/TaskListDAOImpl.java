package com.dgit.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.TaskListVO;
@Repository
public class TaskListDAOImpl implements TasklistDAO{
	private static final String namespace = "com.dgit.mapper.TasklistMapper.";

	@Autowired
	private SqlSession session;

	@Override
	public TaskListVO selectOne(int pno) throws Exception {
		return session.selectOne(namespace + "selectOne", pno);
	}

	@Override
	public List<TaskListVO> selectList() throws Exception {
		return session.selectList(namespace + "selectList");
	}

	@Override
	public int insert(TaskListVO vo) throws Exception {
		return session.insert(namespace + "insert", vo);
	}

	@Override
	public int delete(int pno) throws Exception {
		return session.delete(namespace + "delete", pno);
	}

	@Override
	public int update(TaskListVO vo) throws Exception {
		return session.update(namespace + "update", vo);
	}

	@Override
	public int changeOrder(TaskListVO vo) throws Exception {
		return session.update(namespace + "changeOrder", vo);
	}

	@Override
	public int countTaskAllByPno(int pno) throws Exception {
		return session.selectOne(namespace + "countTaskAllByPno", pno);
	}

	@Override
	public int countTaskFinishByPno(int pno) throws Exception {
		return session.selectOne(namespace + "countTaskFinishByPno", pno);
	}
}
