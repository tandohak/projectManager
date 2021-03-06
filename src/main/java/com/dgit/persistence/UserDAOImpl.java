package com.dgit.persistence;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	private static final String namespace = "com.dgit.mapper.UserMapper.";

	@Autowired
	private SqlSession session;

	@Override
	public UserVO selectOne(int uno) throws Exception {
		return session.selectOne(namespace+"selectOne",uno);
	}

	@Override
	public List<UserVO> selectList() throws Exception {
		return session.selectList(namespace+"selectList");
	}

	@Override
	public int insert(UserVO vo) throws Exception {
		return session.insert(namespace+"insert",vo);
	}

	@Override
	public int delete(int uno) throws Exception {
		return session.delete(namespace+"delete",uno);
	}

	@Override
	public int update(UserVO vo) throws Exception {
		return session.update(namespace+"update",vo);
	}

	@Override
	public UserVO selectOneByEmail(String email) throws Exception {
		return session.selectOne(namespace+"selectOneByEmail",email);
	}

	@Override
	public UserVO readWithPw(String email, String password) throws Exception {
		HashMap<String,String> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		return session.selectOne(namespace+"readWithPw",map);
	}

}
