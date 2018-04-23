package com.dgit.persistence;

import java.util.HashMap;
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

	@Override
	public int countFinishTaskByPno(int pno) throws Exception {
		return session.selectOne(namespace + "countFinishTaskByPno", pno);
	} 
 
	@Override
	public int countProgressingTaskByPno(int pno) throws Exception {
		return session.selectOne(namespace + "countProgressingTaskByPno", pno);
	}

	@Override
	public int countPassedTaskByPno(int pno) throws Exception {
		return session.selectOne(namespace + "countPassedTaskByPno", pno);
	}
   
	@Override
	public int countPlannedTaskByPno(int pno) throws Exception {
		return session.selectOne(namespace + "countPlannedTaskByPno", pno);
	}

	@Override
	public int countNoPlannendTaskByPno(int pno) throws Exception {
		return session.selectOne(namespace + "countNoPlannendTaskByPno", pno);
	}

	@Override
	public int makeMecountFinishTaskByPno(int pno, int massno) throws Exception {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("pno", pno);
		map.put("massno", massno);
		return session.selectOne(namespace + "makeMecountFinishTaskByPno", map);
	}

	@Override
	public int makeMecountProgressingTaskByPno(int pno, int massno) throws Exception {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("pno", pno);
		map.put("massno", massno);
		return session.selectOne(namespace + "makeMecountProgressingTaskByPno", map);
	}

	@Override
	public int makeMecountPassedTaskByPno(int pno, int massno) throws Exception {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("pno", pno);
		map.put("massno", massno);
		return session.selectOne(namespace + "makeMecountPassedTaskByPno", map);
	}

	@Override
	public int makeMecountPlannedTaskByPno(int pno, int massno) throws Exception {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("pno", pno);
		map.put("massno", massno);
		return session.selectOne(namespace + "makeMecountPlannedTaskByPno", map);
	}

	@Override
	public int makeMecountNoPlannendTaskByPno(int pno, int massno) throws Exception {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("pno", pno);
		map.put("massno", massno);
		return session.selectOne(namespace + "makeMecountNoPlannendTaskByPno", map);
	}
     
	@Override
	public int assignmentCountFinishTaskByPnoAndMassno(int pno, int massno) throws Exception {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("pno", pno);
		map.put("massno", massno);
		return session.selectOne(namespace + "assignmentCountFinishTaskByPnoAndMassno", map);
	}

	@Override
	public int assignmentCountPassedTaskByPnoAndMassno(int pno, int massno) throws Exception {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("pno", pno);
		map.put("massno", massno);
		return session.selectOne(namespace + "assignmentCountPassedTaskByPnoAndMassno", map);
	}

	@Override
	public int assignmentCountPlannedTaskByPnoAndMassno(int pno, int massno) throws Exception {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("pno", pno);
		map.put("massno", massno);
		return session.selectOne(namespace + "assignmentCountPlannedTaskByPnoAndMassno", map);
	}

	@Override
	public int assignmentCountNoPlannendTaskByPnoAndMassno(int pno, int massno) throws Exception {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("pno", pno);
		map.put("massno", massno);
		return session.selectOne(namespace + "assignmentCountNoPlannendTaskByPnoAndMassno", map);
	}
  
}  
