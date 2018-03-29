package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgit.domain.UserVO;
import com.dgit.persistence.UserDAO;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO dao;
	
	@Override
	public UserVO selectOne(int uno) throws Exception {
		return dao.selectOne(uno);
	}

	@Override
	public List<UserVO> selectList() throws Exception {
		return dao.selectList();
	}

	@Override
	public int insert(UserVO vo) throws Exception {
		return dao.insert(vo);
	}

	@Override
	public int delete(int uno) throws Exception {
		return dao.delete(uno);
	}

	@Override
	public int update(UserVO vo) throws Exception {
		return dao.update(vo);
	}

	@Override
	public UserVO selectOneByEmail(String email) throws Exception {
		return dao.selectOneByEmail(email);
	}

}
