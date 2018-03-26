package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.ProjectVO;

public interface ProjectDAO {
	public ProjectVO selectOne(int pno) throws Exception;
	public List<ProjectVO> selectList() throws Exception;
	public int insert(ProjectVO vo) throws Exception;
	public int delete(int pno) throws Exception;
	public int update(ProjectVO vo) throws Exception;
}
