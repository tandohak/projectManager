package com.dgit.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dgit.domain.UserVO;
import com.dgit.domain.WorkspaceVO;
import com.dgit.service.UserService;
import com.dgit.service.WorkspaceService;
import com.dgit.util.TempKey;

@RestController
@RequestMapping("/register/*")
public class UserRestController {
	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	UserService userService;
	   
	@Autowired 
	WorkspaceService WorkService;  
	
	@RequestMapping(value = "/create/{wname}", method = {RequestMethod.PUT,RequestMethod.PATCH,})
	public ResponseEntity<HashMap<String, Object>> userCreate(@RequestBody UserVO vo,@PathVariable("wname") String wname) {
		ResponseEntity<HashMap<String, Object>> entity = null;
		logger.info("[user create]");   
		logger.info("[user create]" + vo.toString()); 
		logger.info("[user create]" + wname); 
		     
		try {
			userService.insert(vo);
			  
			WorkspaceVO wvo = new WorkspaceVO();
			wvo.setName(wname);  
			wvo.setUno(vo.getUno());
			wvo.setMaker(vo.getFirstName() + " " + vo.getLastName());
			
			String res = WorkService.insert(wvo);
			
			if(res.equals("fail")){
				throw new Exception("WorkSpace make fail");
			}
			wvo.setWcode(res);
			HashMap<String, Object> map = new HashMap<>();
			
			map.put("email", vo.getEmail());
			map.put("wvo", wvo);
			
			entity = new ResponseEntity<>(map,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
		
		return entity; 
	}
	
	@RequestMapping(value = "/exists", method =RequestMethod.POST)
	public ResponseEntity<String> userSelectOne(@RequestBody String email) {
		ResponseEntity<String> entity = null;
		
		try{
			System.out.println("============="+email);
			UserVO vo= userService.selectOneByEmail(email);
			System.out.println("============="+vo);
			if(vo !=null){ 
				entity = new ResponseEntity<>("exists",HttpStatus.OK);
			}else{
				entity = new ResponseEntity<>("notExists",HttpStatus.OK);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
		}
		
		return entity; 
	}
}  
