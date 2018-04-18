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
			String taskName = (String) map.get("taskName");
			String endDate= (String)map.get("endDate");
			int tlno = (int)map.get("tlno");
			
			TaskVO vo = new TaskVO();
			vo.setTlno(tlno);
			vo.setTaskname(taskName);
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
	public ResponseEntity<String> taskListMake(@PathVariable int tlno) {
		ResponseEntity<String> entity = null;
		try { 
			tasklistService.delete(tlno);
			
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
}   
