package com.dgit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dgit.domain.MemberVO;
import com.dgit.service.MemberService;

@RestController
@RequestMapping("/member/*")
public class MemberRestController {
	@Autowired
	private MemberService memService;
	
	@RequestMapping(value="/update",method = RequestMethod.PATCH)
	public ResponseEntity<String> memUpdate(@RequestBody MemberVO vo){
		ResponseEntity<String> entity = null;
		System.out.println("업데이트" + vo); 
		try{ 
			memService.update(vo);  
			entity = new ResponseEntity<>("success",HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		
		return entity;
	}
	
	@RequestMapping(value="/memList",method = RequestMethod.GET)
	public ResponseEntity<List<MemberVO>> memberListByWcode(String wcode){
		ResponseEntity<List<MemberVO>> entity = null; 
		
		try{
			List<MemberVO> memList= memService.selectListByWcode(wcode);
			entity = new ResponseEntity<>(memList,HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		
		return entity; 
	}
	
	@RequestMapping(value="/memList/search",method = RequestMethod.GET)
	public ResponseEntity<List<MemberVO>> memberListByWcodeSearch(String wcode,String name){
		ResponseEntity<List<MemberVO>> entity = null;  
		
		try{
			List<MemberVO> memList= memService.searchMemberByName(name, wcode);
			entity = new ResponseEntity<>(memList,HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}     
		
		return entity; 
	}
	
	@RequestMapping(value = "/memAssList/{pno}") 
	public ResponseEntity<List<MemberVO>> memAssList(@PathVariable int pno) {
		ResponseEntity<List<MemberVO>> entity = null; 
		try {     
			List<MemberVO> memList = memService.selectListByPnoWithMemAssignment(pno);
			entity = new ResponseEntity<>(memList, HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;      
	}  
} 
