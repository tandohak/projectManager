package com.dgit.projectManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dgit.domain.MemberVO;
import com.dgit.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberDAOTest {
	
	@Autowired
	private MemberDAO dao;
	
	@Test
	public void testCreate() throws Exception{
		MemberVO vo = new MemberVO();
		vo.setUno(1);
		vo.setWcode("s");
		System.out.println(vo);
		dao.insert(vo);
	}  
	
//	@Test
	public void testRead() throws Exception{
	}
	
//	@Test
	public void testUpdate() throws Exception{
	}
	
	
//	@Test
	public void testListAll() throws Exception{
	}
	
//	@Test
	public void testDelete() throws Exception{
	}
	 
}
