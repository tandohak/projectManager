package com.dgit.service;

import java.util.List;

import com.dgit.domain.InviteVO;
import com.dgit.domain.WorkspaceVO;

public interface WorkspaceService {
	public WorkspaceVO selectOne(String wcode) throws Exception;
	public List<WorkspaceVO> selectList() throws Exception;
	public String insert(WorkspaceVO vo) throws Exception;
	public int delete(String wcode) throws Exception;
}
