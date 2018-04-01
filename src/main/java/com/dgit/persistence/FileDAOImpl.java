package com.dgit.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.dgit.domain.FileVO;

public class FileDAOImpl implements FileDAO {

	private static final String namespace = "com.dgit.mapper.FileMapper.";
	
	@Autowired
	private SqlSession session;
	
	
	@Override
	public FileVO selectOne(int fno) throws Exception {
		return session.selectOne(namespace+"selectOne",fno);
	}

	@Override
	public List<FileVO> selectList() throws Exception { 
		return session.selectList(namespace+"selectList");
	}

	@Override
	public int insert(FileVO vo) throws Exception {
		return session.insert(namespace+"insert",vo);
	}

	@Override
	public int delete(int fno) throws Exception {
		return session.delete(namespace+"delete",fno);
	}

	@Override
	public int update(FileVO vo) throws Exception {
		return session.update(namespace+"update",vo);
	}


}
