package com.dgit.persistence;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.MemberVO;
import com.dgit.domain.UserVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	private static final String namespace = "com.dgit.mapper.MemberMapper.";

	@Autowired
	private SqlSession session;
	

	@Override
	public MemberVO selectOne(int mno) throws Exception {
		return session.selectOne(namespace+"selectOne",mno);
	}

	@Override
	public List<MemberVO> selectList() throws Exception { 
		return session.selectList(namespace+"selectList");
	}

	@Override
	public int insert(MemberVO vo) throws Exception {
		return session.insert(namespace+"insert",vo);
	}

	@Override
	public int delete(int mno) throws Exception {
		return session.delete(namespace+"delete",mno);
	}

	@Override
	public int update(MemberVO vo) throws Exception {
		return session.update(namespace+"update",vo);
	}

	@Override
	public List<MemberVO> selectListByWcode(String wcode) throws Exception {
		return session.selectList(namespace+"selectListByWcode",wcode);
	}

	@Override
	public  List<MemberVO> selectListByUno(int uno) throws Exception {
		return session.selectList(namespace+"selectListByUno",uno);
	}

	@Override
	public List<MemberVO> selectListByUnoJoinWorkspace(int uno) throws Exception {
		return session.selectList(namespace+"selectListByUnoJoinWorkspace",uno);
	}
	
	@Override 
	public MemberVO selectOneUnoAndwcode(MemberVO vo) throws Exception {
		return  session.selectOne(namespace+"selectOneUnoAndwcode",vo);
	}
	
	@Override
	public List<MemberVO> searchMemberByName(String name,String wcode) throws Exception {
		HashMap<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("wcode", wcode);
		 
		return session.selectList(namespace+"searchMemberByName",map);
	}

	@Override
	public List<MemberVO> selectListByPno(int pno) throws Exception {
		return session.selectList(namespace+"selectListByPno",pno);
	}

	@Override
	public MemberVO selectOneByPnoAndUno(int uno, int pno) throws Exception {
		HashMap<String,Integer> map = new HashMap<>();
		map.put("uno",uno);
		map.put("pno",pno); 
		return  session.selectOne(namespace+"selectOneByPnoAndUno",map);
	}
 
	@Override
	public List<MemberVO> selectListByPnoWithMemAssignment(int pno) throws Exception {
		return session.selectList(namespace+"selectListByPnoWithMemAssignment",pno);
	}

	@Override
	public List<MemberVO> selectListByTasknoWithMemAssignment(int taskno) throws Exception {
		return session.selectList(namespace+"selectListByTasknoWithMemAssignment",taskno);
	} 
	  
}