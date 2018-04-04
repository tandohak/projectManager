package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.InviteVO;

public interface InviteDAO {
	public List<InviteVO> selectListByWno(String wcode) throws Exception;
	public List<InviteVO> selectListBywcode(InviteVO vo) throws Exception;
	public int insert(InviteVO vo) throws Exception;
	public int delete(int ino) throws Exception;
	public int update(InviteVO vo) throws Exception;
}
