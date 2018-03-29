package com.dgit.persistence;

import com.dgit.domain.EmailAuthVO;

public interface EmailAuthDAO {
	public EmailAuthVO selectOne(EmailAuthVO vo) throws Exception;
	public int insert(EmailAuthVO vo) throws Exception;
	public int delete(EmailAuthVO vo) throws Exception;
}
