package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.WorkspaceVO;

public interface WorkspaceDAO {
	public WorkspaceVO selectOne(int Wno) throws Exception;
	public List<WorkspaceVO> selectList() throws Exception;
	public int insert(WorkspaceVO vo) throws Exception;
	public int delete(int Wno) throws Exception;
}
