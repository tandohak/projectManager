package com.dgit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
} 
