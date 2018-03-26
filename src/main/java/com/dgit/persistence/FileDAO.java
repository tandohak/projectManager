package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.FileVO;

public interface FileDAO {
	public FileVO selectOne(int fno) throws Exception;
	public List<FileVO> selectList() throws Exception;
	public int insert(FileVO vo) throws Exception;
	public int delete(int fno) throws Exception;
	public int update(FileVO vo) throws Exception;
}
