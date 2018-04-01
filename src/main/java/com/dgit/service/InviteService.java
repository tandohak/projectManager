package com.dgit.service;

import java.util.List;

import com.dgit.domain.InviteVO;

public interface InviteService {
	public List<InviteVO> selectListByWno(int wno) throws Exception;
	public int insert(InviteVO vo) throws Exception;
	public int delete(int ino) throws Exception;
	public int update(InviteVO vo) throws Exception;
}
