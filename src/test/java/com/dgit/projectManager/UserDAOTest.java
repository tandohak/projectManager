package com.dgit.projectManager;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dgit.domain.UserVO;
import com.dgit.persistence.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class UserDAOTest {
	
	@Autowired
	private UserDAO dao;
	
//	@Test
	public void testCreate() throws Exception{
		UserVO vo = new UserVO();
		vo.setEmail("ggg");
		vo.setFirstName("홍");
		vo.setLastName("길");
		vo.setPhone("010-123-1234");
		vo.setAddr("대구광역시");
		vo.setBirthday(new Date());
		vo.setGrade(0);
		vo.setJoinDate(new Date());
		vo.setPhotoPath("path");
		dao.insert(vo);
	}  
	
//	@Test
	public void testRead() throws Exception{
		dao.selectOne(1);
	}
	
//	@Test
	public void testUpdate() throws Exception{
		UserVO vo = new UserVO();
		vo.setUno(1);
		vo.setEmail("eee");
		vo.setFirstName("홍");
		vo.setLastName("길");
		vo.setPhone("010-123-1234");
		vo.setAddr("대구광역시");
		vo.setBirthday(new Date());
		vo.setGrade(0);
		vo.setJoinDate(new Date());
		vo.setPhotoPath("path");
		dao.update(vo);
	}
	
	
//	@Test
	public void testListAll() throws Exception{
		dao.selectList();
	}
	
//	@Test
	public void testDelete() throws Exception{
		dao.delete(1);
	}
	
	 
}
