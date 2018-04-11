package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.MemAssignmentVO;

public interface MemAssignmentDAO {
	public MemAssignmentVO selectOne(MemAssignmentVO vo) throws Exception;
	public List<MemAssignmentVO> selectList(int pno) throws Exception;
	public int insert(MemAssignmentVO vo) throws Exception;
	public int delete(MemAssignmentVO vo) throws Exception;
	public int update(MemAssignmentVO vo) throws Exception;
}
