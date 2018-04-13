package com.dgit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dgit.domain.MemAssignmentVO;
import com.dgit.domain.MemberVO;
import com.dgit.domain.ProjectVO;
import com.dgit.domain.TaskListVO;
import com.dgit.service.MemAssignmentService;
import com.dgit.service.MemberService;
import com.dgit.service.ProjectService;
import com.dgit.service.TaskListService;
import com.dgit.service.TaskService;

@RestController
@RequestMapping("/project/*")
public class ProjectRestController {

	@Autowired
	private MemAssignmentService memAssService;

	@Autowired
	private ProjectService projectService;
 
	@Autowired
	private TaskListService tasklistService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private MemberService memService;

	@RequestMapping(value = "/make", method = RequestMethod.POST)
	public ResponseEntity<ProjectVO> makeProject(@RequestBody HashMap<String, Object> map) {
		ResponseEntity<ProjectVO> entity = null;

		try {
			int makerMno = (Integer) map.get("makerMno");
			ProjectVO vo = new ProjectVO();
			vo.setTitle((String) map.get("title"));
			vo.setExplanation((String) map.get("explanation"));
			vo.setWcode((String) map.get("wcode"));
			vo.setMaker(makerMno);   
			//1 = 공개 0 = 비공개
			if (((String) map.get("visibility")).equals("1")) {
				vo.setVisibility(true); 
			} else {
				vo.setVisibility(false);
			}
  
			List<Integer> memList = (List<Integer>) map.get("memList");
			int res = projectService.insert(vo, memList, makerMno);

			if (res < 0) {
				throw new Exception("project 생성 실패");
			}
			int template = (Integer) map.get("template");
  
			// template : 0 - 없음 , 1 - 평일 , 2 - 개인 , 3 - 부서 , 4 - 칸반
			// taskList 생성
			switch (template) {
			case 1:
				String[] dayArr = { "월요일", "화요일", "수요일", "목요일", "금요일" };
				System.out.println("dayArr - " + dayArr[0]);
				for (int i = 0; i < 5; i++) {
					TaskListVO listVo = new TaskListVO();
					listVo.setList_order(i);
					listVo.setPno(vo.getPno());
					listVo.setName(dayArr[i]);
					System.out.println("테스크 리스트 - " + listVo);
					tasklistService.insert(listVo);
				}
				break;
			case 2:
				for (int i = 0; i < memList.size(); i++) {
					TaskListVO listVo = new TaskListVO();
					listVo.setList_order(i);
					listVo.setPno(vo.getPno());
					MemberVO memVO = memService.selectOne(memList.get(i));
					listVo.setName(memVO.getFirstName() + memVO.getLastName());
					tasklistService.insert(listVo);
				}
				break;
			case 3:
				String[] dayArr2 = { "기획팀", "개발팀", "디자인팀" };

				for (int i = 0; i < dayArr2.length; i++) {
					TaskListVO listVo = new TaskListVO();
					listVo.setList_order(i);
					listVo.setPno(vo.getPno());
					listVo.setName(dayArr2[i]);
					tasklistService.insert(listVo);
				}
				break;
			case 4:
				String[] arr = { "해야할 일", "진행중", "완료" };

				for (int i = 0; i < arr.length; i++) {
					TaskListVO listVo = new TaskListVO();
					listVo.setList_order(i);
					listVo.setPno(vo.getPno());
					listVo.setName(arr[i]);
					tasklistService.insert(listVo);
				}
				break;
			}

			entity = new ResponseEntity<>(vo, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}  

		return entity;   
	}  
	
	@RequestMapping(value = "/select/{pno}", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String,Object>> selectProject(@PathVariable int pno) {
		ResponseEntity<HashMap<String,Object>> entity = null;
		try { 
			ProjectVO vo = projectService.selectOne(pno); ;
			List<MemberVO> member = memService.selectListByPno(pno);
			HashMap<String,Object> map = new HashMap<>();
			map.put("project", vo);
			map.put("member",member);    
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;  
	}
	 
	@RequestMapping(value = "/update/title/{pno}", method = {RequestMethod.PATCH,RequestMethod.PUT})
	public ResponseEntity<String> updateTitle(@PathVariable int pno,@RequestBody String title) {
		ResponseEntity<String> entity = null;
		try {  
			ProjectVO vo =projectService.selectOne(pno); ;
			vo.setTitle(title);     
			System.out.println("타이틀"+title);
			projectService.update(vo);
			
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;  
	}
	
	@RequestMapping(value = "/update/explanation/{pno}", method = {RequestMethod.PATCH,RequestMethod.PUT})
	public ResponseEntity<String> updateExplanation(@PathVariable int pno,@RequestBody String explanation) {
		ResponseEntity<String> entity = null;
		try {  
			ProjectVO vo =projectService.selectOne(pno); ;
			vo.setExplanation(explanation);
			projectService.update(vo); 
			
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;  
	}      
	  
	@RequestMapping(value = "/update/status/{pno}/{status}", method = {RequestMethod.PATCH,RequestMethod.PUT})
	public ResponseEntity<String> updateStatus(@PathVariable int pno,@PathVariable int status) {
		ResponseEntity<String> entity = null;
		try {  
			ProjectVO vo =projectService.selectOne(pno); ;
			vo.setStatus(status);
			projectService.update(vo); 
			
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;         
	}     
	                            
	@RequestMapping(value = "/update/startDate/{pno}", method = {RequestMethod.PATCH,RequestMethod.PUT})
	public ResponseEntity<String> updateStartDate(@PathVariable int pno,@RequestBody String startDate) {
		ResponseEntity<String> entity = null;    
		try {      
			ProjectVO vo =projectService.selectOne(pno); 
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formater.parse(startDate);
			vo.setStartDate(date);
 			
			projectService.update(vo);            
			 
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;  
	}  
	
	@RequestMapping(value = "/update/endDate/{pno}", method = {RequestMethod.PATCH,RequestMethod.PUT})
	public ResponseEntity<String> updateEndDate(@PathVariable int pno,@RequestBody String endDate) {
		ResponseEntity<String> entity = null;
		try {  
			ProjectVO vo =projectService.selectOne(pno);
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formater.parse(endDate);
 			 
			vo.setEndDate(date);
			projectService.update(vo); 
			 
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;  
	}
	
	@RequestMapping(value = "/update/finishDate/{pno}", method = {RequestMethod.PATCH,RequestMethod.PUT})
	public ResponseEntity<String> updateFinishDate(@PathVariable int pno,@RequestBody String finishDate) {
		ResponseEntity<String> entity = null;
		try {    
			ProjectVO vo =projectService.selectOne(pno); ;
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formater.parse(finishDate);
			vo.setFinishDate(date);
			projectService.update(vo); 
			
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;  
	}
	
	@RequestMapping(value = "/update/memAssGrade/{pno}/{mno}", method = {RequestMethod.PATCH,RequestMethod.PUT})
	public ResponseEntity<String> updateMemAssGrade(@PathVariable int pno,@PathVariable int mno,@RequestBody int memAssGrade) {
		ResponseEntity<String> entity = null;
		try {  
			MemAssignmentVO vo = new MemAssignmentVO();
			vo.setPno(pno);
			vo.setMno(mno); 
			vo.setGrade(memAssGrade);
			memAssService.update(vo);
			
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;  
	}
	   
	@RequestMapping(value = "/delete/memAssGrade/{pno}/{mno}", method = {RequestMethod.PATCH,RequestMethod.PUT})
	public ResponseEntity<String> updateMemAssGrade(@PathVariable int pno,@PathVariable int mno) {
		ResponseEntity<String> entity = null;
		try {  
			MemAssignmentVO vo = new MemAssignmentVO();
			vo.setPno(pno);
			vo.setMno(mno);  
			memAssService.delete(vo);
			
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;  
	}
} 
