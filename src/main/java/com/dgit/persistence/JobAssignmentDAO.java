package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.JobAssignmentVO;
import com.dgit.domain.JobAssignmentVO;

public interface JobAssignmentDAO {
	public JobAssignmentVO selectOne(JobAssignmentVO vo) throws Exception;
	public List<JobAssignmentVO> selectList() throws Exception;
	public List<JobAssignmentVO> selectTaskNo(int taskno) throws Exception;
	public int insert(JobAssignmentVO vo) throws Exception;
	public int delete(JobAssignmentVO vo) throws Exception;
	public int deleteByTaskno(int taskno) throws Exception;
	public int update(JobAssignmentVO vo) throws Exception;
}
