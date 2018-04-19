package com.dgit.service;

import java.util.List;

import com.dgit.domain.MemAssignmentVO;

public interface MemAssignmentService {
	public MemAssignmentVO selectOne(MemAssignmentVO vo) throws Exception;
	public List<MemAssignmentVO> selectList(int pno) throws Exception;
	public int insert(MemAssignmentVO vo) throws Exception;
	public int delete(MemAssignmentVO vo) throws Exception;
	public int update(MemAssignmentVO vo) throws Exception;
	public List<MemAssignmentVO> selectListByTaskno(int taskno) throws Exception;

}
