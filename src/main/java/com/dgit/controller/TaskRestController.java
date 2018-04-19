package com.dgit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dgit.domain.JobAssignmentVO;
import com.dgit.domain.MemAssignmentVO;
import com.dgit.domain.MemberVO;
import com.dgit.domain.ProjectVO;
import com.dgit.domain.TaskListVO;
import com.dgit.domain.TaskVO;
import com.dgit.service.JobAssignmentService;
import com.dgit.service.MemAssignmentService;
import com.dgit.service.MemberService;
import com.dgit.service.TaskListService;
import com.dgit.service.TaskService;

@RestController         
@RequestMapping("/taskList/*")
public class TaskRestController {
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskListService tasklistService;
	
	@Autowired 
	private MemberService memService;
	@Autowired
	private MemAssignmentService memAssService;  

	@Autowired    
	private JobAssignmentService jobAssignmentService;
	
	@RequestMapping(value = "/register/taskmake", method = RequestMethod.POST)
	public ResponseEntity<TaskVO> jobAssignment(@RequestBody HashMap<String,Object> map) {
		ResponseEntity<TaskVO> entity = null;
		try {     
			List<String> jobAssList = (List<String>) map.get("jobAssList");
			String taskname = (String) map.get("taskname");
			String endDate= (String)map.get("endDate");
			String writer= (String)map.get("writer");
			int tlno = (int)map.get("tlno");
			    
			TaskVO vo = new TaskVO();
			vo.setTlno(tlno);  
			vo.setTaskname(taskname);  
			vo.setWriter(writer);  
			
			if(!endDate.equals("")){
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date date =formater.parse(endDate); 
				vo.setEndDate(date);
			}   
			
			int res = taskService.insert(vo);
			 
			if(res < 0){  
				System.out.println("task insert res -- " + res);
				throw new Exception();
			}      
			   
			if(jobAssList.size() > 0){
				for(String massno : jobAssList){ 
					JobAssignmentVO jobAssVO = new JobAssignmentVO();
					jobAssVO.setTaskno(vo.getTaskno());
					jobAssVO.setMassno(Integer.parseInt(massno));
					jobAssignmentService.insert(jobAssVO);
				}
			}      
			   
			entity = new ResponseEntity<>(vo, HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;   
	}
	
	@RequestMapping(value = "/register/taskListmake", method = RequestMethod.POST)
	public ResponseEntity<TaskListVO> taskListMake(@RequestBody TaskListVO vo) {
		ResponseEntity<TaskListVO> entity = null;
		try {
			tasklistService.insert(vo);
			entity = new ResponseEntity<>(vo, HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;   
	}
	 
	@RequestMapping(value = "/delete/taskList/{tlno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> taskListDelete(@PathVariable int tlno) {
		ResponseEntity<String> entity = null;
		try { 
			List<TaskVO> list = taskService.selectListWithTlno(tlno);
			for(TaskVO task :list){
				jobAssignmentService.deleteByTaskno(task.getTaskno());
			}
			
			taskService.deleteByTlno(tlno);
			  
			tasklistService.delete(tlno);
			 
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {  
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;   
	}   
	 
	@RequestMapping(value = "/update/taskList/{tlno}/{order}", method ={ RequestMethod.PUT,  RequestMethod.PATCH})
	public ResponseEntity<String> updateTaskListOrder(@PathVariable int tlno,@PathVariable int order) {
		ResponseEntity<String> entity = null;
		try {  
			TaskListVO vo = new TaskListVO();   
			vo.setTlno(tlno);
			vo.setList_order(order);         
			
			tasklistService.changeOrder(vo);
			entity = new ResponseEntity<>("success", HttpStatus.OK); 
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();  
		}        
		return entity;    
	}       
	@RequestMapping(value = "/update/taskStatus",method={RequestMethod.PUT,RequestMethod.PATCH})
	public ResponseEntity<String> taskStateUpdate(@RequestBody TaskVO vo) {
		ResponseEntity<String> entity = null;    
		try {   
			System.out.println("TaskVO ------ " + vo);    
			TaskVO tempVO = taskService.selectOne(vo.getTaskno());
			System.out.println("tempVO ------ " + tempVO);
			if(tempVO!=null){     
				tempVO.setStatus(vo.getStatus());
				taskService.update(tempVO);  	  
			}    
			          
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;   
	}
	
	@RequestMapping(value = "/select/task/{taskno}", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String,Object>> selectTask(@PathVariable int taskno) {
		ResponseEntity<HashMap<String,Object>> entity = null;
		try { 
			TaskVO vo= taskService.selectOne(taskno); 
			
			List<MemberVO> member = memService.selectListByTasknoWithMemAssignment(taskno);
			  
			HashMap<String,Object> map = new HashMap<>();
			map.put("task", vo); 
			map.put("member", member);
			   
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		} 
		return entity;   
	} 
	
	@RequestMapping(value = "/update/startDate/{taskno}", method = {RequestMethod.PATCH,RequestMethod.PUT})
	public ResponseEntity<String> updateStartDate(@PathVariable int taskno,@RequestBody String startDate) {
		ResponseEntity<String> entity = null;    
		try {      
			TaskVO vo = taskService.selectOne(taskno);
			
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = null; 
			 
			System.out.println(startDate);
			     
			if(!startDate.equalsIgnoreCase("del")){  
				date = formater.parse(startDate); 
			}    
			     
			vo.setStartDate(date);        
 			  
			taskService.update(vo);            
			   
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;  
	}   
	
	@RequestMapping(value = "/update/endDate/{taskno}", method = {RequestMethod.PATCH,RequestMethod.PUT})
	public ResponseEntity<String> updateEndDate(@PathVariable int taskno,@RequestBody String endDate) {
		ResponseEntity<String> entity = null;    
		try {      
			TaskVO vo = taskService.selectOne(taskno);
			
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = null; 
			 
			System.out.println(endDate);
			     
			if(!endDate.equalsIgnoreCase("del")){
				date = formater.parse(endDate); 
			}    
			   
			vo.setEndDate(date);       
 			      
			taskService.update(vo);            
			   
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;  
	}  
	
	@RequestMapping(value = "/update/finishDate/{taskno}", method = {RequestMethod.PATCH,RequestMethod.PUT})
	public ResponseEntity<String> updateFinishDate(@PathVariable int taskno,@RequestBody String finishDate) {
		ResponseEntity<String> entity = null;    
		try {      
			TaskVO vo = taskService.selectOne(taskno);
			
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = null; 
			 
			System.out.println(finishDate);
			     
			if(!finishDate.equalsIgnoreCase("del")){
				date = formater.parse(finishDate); 
			}    
			   
			vo.setFinishDate(date);     
 			    
			taskService.update(vo);            
			   
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;  
	}
	 
	@RequestMapping(value = "/insert/jobassignment/", method = RequestMethod.POST)
	public ResponseEntity<String> insertJobassignment(@RequestBody JobAssignmentVO vo) {
		ResponseEntity<String> entity = null;    
		try {      
			 
			JobAssignmentVO tempVo = jobAssignmentService.selectOne(vo);
			 System.out.println("vo---------"+vo);   
			if(tempVo == null){
				jobAssignmentService.insert(vo); 
			}
 			    
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {     
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;  
	}
	
	@RequestMapping(value = "/delete/jobassignment/", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletJobassignment(@RequestBody JobAssignmentVO vo) {
		ResponseEntity<String> entity = null;    
		try {        
			jobAssignmentService.delete(vo); 
 			        
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {     
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;  
	}
	
	
	@RequestMapping(value = "/update/title/{taskno}", method = {RequestMethod.PATCH,RequestMethod.PUT})
	public ResponseEntity<String> updateTaskTitle(@PathVariable int taskno,@RequestBody String title) {
		ResponseEntity<String> entity = null; 
		try {  
			TaskVO vo = taskService.selectOne(taskno);
			vo.setTaskname(title);     
			System.out.println("타이틀"+title);
			taskService.update(vo);
			
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;   
	}
	
	@RequestMapping(value = "/update/explanation/{taskno}", method = {RequestMethod.PATCH,RequestMethod.PUT})
	public ResponseEntity<String> updateTaskExplanation(@PathVariable int taskno,@RequestBody String explanation) {
		ResponseEntity<String> entity = null;
		try {  
			 
			TaskVO vo = taskService.selectOne(taskno);
			vo.setExplanation(explanation);   
			taskService.update(vo);
			
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) { 
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;  
	} 
}    
