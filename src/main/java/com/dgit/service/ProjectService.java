package com.dgit.service;

import java.util.List;

import com.dgit.domain.ProjectVO;

public interface ProjectService {
	public ProjectVO selectOne(int pno) throws Exception;
	public List<ProjectVO> selectList() throws Exception;
	public int insert(ProjectVO vo,List<Integer> memList,int makerMno) throws Exception;
	public int delete(int pno) throws Exception;
	public int update(ProjectVO vo) throws Exception;
	public List<ProjectVO> selectListByWcode(String wcode) throws Exception;

}
