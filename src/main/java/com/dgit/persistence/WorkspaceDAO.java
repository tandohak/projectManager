package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.WorkspaceVO;

public interface WorkspaceDAO {
	public WorkspaceVO selectOne(String wcode) throws Exception;
	public List<WorkspaceVO> selectList() throws Exception;
	public int insert(WorkspaceVO vo) throws Exception;
	public int delete(String wcode) throws Exception;
	public int update(WorkspaceVO vo) throws Exception;
}
