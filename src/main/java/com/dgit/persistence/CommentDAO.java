package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.CommentVO;

public interface CommentDAO {
	public CommentVO selectOne(int cno) throws Exception;
	public List<CommentVO> selectList() throws Exception;
	public int insert(CommentVO vo) throws Exception;
	public int delete(int cno) throws Exception;
	public int update(CommentVO vo) throws Exception;
}
