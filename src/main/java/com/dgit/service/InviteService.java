package com.dgit.service;

import java.util.List;

import com.dgit.domain.InviteVO;

public interface InviteService {
	public List<InviteVO> selectListByWno(String wno) throws Exception;
	public List<InviteVO> selectListBywcodeAndInvitee(InviteVO vo) throws Exception;
	public List<InviteVO> selectListBywcode(String wcode) throws Exception;
	public int insert(InviteVO vo) throws Exception;
	public int delete(String invitee) throws Exception;
	public int update(InviteVO vo) throws Exception;
}
