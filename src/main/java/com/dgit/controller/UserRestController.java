package com.dgit.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.dgit.domain.LoginDTO;
import com.dgit.domain.MemberVO;
import com.dgit.domain.UserVO;
import com.dgit.domain.WorkspaceVO;
import com.dgit.service.MemberService;
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
	
	@Autowired 
	MemberService memService;  
	
	@RequestMapping(value = "/create/{wname}", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> userCreate(@RequestBody UserVO vo,@PathVariable("wname") String wname,HttpSession session) {
		ResponseEntity<HashMap<String, Object>> entity = null;
		logger.info("[user create]");   
		logger.info("[user create]" + vo.toString()); 
		logger.info("[user create]" + wname);      
		     
		try {    
		     
			if("" ==vo.getPassword()){
				String key = new TempKey().getKey(20, false);
				vo.setPassword(key);
			}
			
			userService.insert(vo);
			    
			WorkspaceVO wvo = new WorkspaceVO();
			wvo.setName(wname);  
			wvo.setUno(vo.getUno()); 
			wvo.setMaker(vo.getFirstName() + " " + vo.getLastName());
			vo.setPassword("");
			String res = WorkService.insert(wvo);
			
			if(res.equals("fail")){
				throw new Exception("WorkSpace make fail");
			}
			wvo.setWcode(res);
			HashMap<String, Object> map = new HashMap<>();
			
			List<MemberVO> memList = memService.selectListByWcode(res);
			MemberVO mem = null;	
			
			for(MemberVO memVo : memList){
				if(memVo.getUno() == vo.getUno()){
					mem = memVo;
				}
			}
			map.put("email", vo.getEmail());
			map.put("wvo", wvo);
			map.put("userVo", vo); 
			map.put("memVo", mem); 
			
			LoginDTO dto = new LoginDTO();
			dto.setUno(vo.getUno());
			dto.setEmail(vo.getEmail());
			dto.setUsername(vo.getFirstName() + " "+ vo.getLastName());
			dto.setPhotoPath(vo.getPhotoPath());
			session.setAttribute("login", dto);
			
			entity = new ResponseEntity<>(map,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
		
		return entity; 
	}
	
	@RequestMapping(value = "/create/workspace", method = RequestMethod.POST)
	public ResponseEntity<WorkspaceVO> workspaceCreate(@RequestBody WorkspaceVO vo) {
		ResponseEntity<WorkspaceVO> entity = null;
		logger.info("[user create]");   
		logger.info("[user create]" + vo.toString()); 
		     
		try {     
			
//			WorkspaceVO wvo = new WorkspaceVO();
//			wvo.setName(wname);  
//			wvo.setUno(vo.getUno()); 
//			wvo.setMaker(vo.getFirstName() + " " + vo.getLastName());
			
			String res = WorkService.insert(vo);
			
			if(res.equals("fail")){
				throw new Exception("WorkSpace make fail");
			}
			vo.setWcode(res);
			
			
			entity = new ResponseEntity<>(vo,HttpStatus.OK);
		} catch (Exception e) { 
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
		
		return entity; 
	}
	
	@RequestMapping(value = "/update/workspace", method = RequestMethod.POST) 
	public ResponseEntity<WorkspaceVO> workspaceUpdate(@RequestBody WorkspaceVO vo) {
		ResponseEntity<WorkspaceVO> entity = null;
		logger.info("[user create]");   
		logger.info("[user create]" + vo.toString()); 
		     
		try {     
			
			int res = WorkService.update(vo);
			
			entity = new ResponseEntity<>(vo,HttpStatus.OK);
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
	
	@RequestMapping(value = "/googleLogin", method =RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> userGoogleLogin(@RequestBody String email) {
		ResponseEntity<	HashMap<String, Object>> entity = null;
		
		try{ 
			UserVO vo= userService.selectOneByEmail(email);
			vo.setPassword("");
			
			List<MemberVO> memList = memService.selectListByUno(vo.getUno());
			MemberVO mem = memList.get(0);	
			
			WorkspaceVO wvo = WorkService.selectOne(mem.getWcode());
			
			HashMap<String, Object> map = new HashMap<>();
			
			map.put("wvo", wvo);
			map.put("userVo", vo); 
			map.put("memVo", mem); 
			entity = new ResponseEntity<>(map,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;  
	}
	
	 
	@RequestMapping(value = "/emailLogin", method =RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> userEmailLogin(@RequestBody LoginDTO dto) {
		ResponseEntity<HashMap<String, Object>> entity = null;
		
		try{
			UserVO vo = userService.readWithPw(dto.getEmail(), dto.getPassword());
			vo.setPassword("");
			
			List<MemberVO> memList = memService.selectListByUno(vo.getUno());
			MemberVO mem = memList.get(0);	
			
			WorkspaceVO wvo = WorkService.selectOne(mem.getWcode());
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("wvo", wvo);
			map.put("userVo", vo); 
			map.put("memVo", mem); 
			entity = new ResponseEntity<>(map,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;  
	} 
}  
