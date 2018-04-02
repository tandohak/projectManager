package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.UserVO;

public interface UserDAO {
	public UserVO selectOne(int uno) throws Exception;
	public UserVO readWithPw(String email, String password) throws Exception;
	public List<UserVO> selectList() throws Exception;
	public int insert(UserVO vo) throws Exception;
	public int delete(int uno) throws Exception;
	public int update(UserVO vo) throws Exception;
	public UserVO selectOneByEmail(String email) throws Exception;
}
