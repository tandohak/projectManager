package com.dgit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dgit.domain.InviteVO;
import com.dgit.domain.LoginDTO;
import com.dgit.domain.MemberVO;
import com.dgit.domain.ProjectVO;
import com.dgit.domain.WorkspaceVO;
import com.dgit.service.InviteService;
import com.dgit.service.MemberService;
import com.dgit.service.ProjectService;
import com.dgit.service.TaskListService;
import com.dgit.service.WorkspaceService;

@Controller
@RequestMapping("/task/*")
public class TaskController {
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;

	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;

	@Autowired
	private WorkspaceService workService;

	@Autowired
	private MemberService memService;
	
	@Autowired
	private InviteService inviteService;
	
	@Autowired
	private ProjectService projectService;

	@Autowired
	private TaskListService taskListService;
	
	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
  
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String project(Model model,HttpSession session) throws Exception {
		
		Object object = session.getAttribute("login");
		
		LoginDTO login = (LoginDTO)object;
		logger.info("login" + login);
		
		List<MemberVO> memList = memService.selectListByUno(login.getUno());
		MemberVO mem = memList.get(0);
		String wcode = mem.getWcode();
		  
		return "redirect:/task/"+wcode;
	}
	
	@RequestMapping(value = "/{wcode}", method = RequestMethod.GET)
	public String project(Model model,HttpSession session,@PathVariable("wcode") String wcode) throws Exception {
		Object object = session.getAttribute("login");
		LoginDTO login = (LoginDTO)object;
		MemberVO vo = new MemberVO();
		vo.setUno(login.getUno());
		vo.setWcode(wcode);
		MemberVO loginMem = memService.selectOneUnoAndwcode(vo);
		List<MemberVO> workMembers = memService.selectListByWcode(wcode);
		
		List<ProjectVO> projectList = projectService.selectListByWcode(wcode);
		
		List<HashMap<String,Object>> pjlist= new ArrayList<>();
		
		for(ProjectVO pj : projectList){
			HashMap<String,Object> map = new HashMap();
			int countAll = taskListService.countTaskAllByPno(pj.getPno());
			int countFinish = taskListService.countTaskFinishByPno(pj.getPno());
			map.put("pj", pj);
			map.put("countAll", countAll);
			map.put("countFinish", countFinish);
			pjlist.add(map);
		}
		
		model.addAttribute("wcode",wcode); 
		model.addAttribute("workMembers",workMembers);
		model.addAttribute("loginMem",loginMem); 
		model.addAttribute("projectList",pjlist);
		
		return "task/project_list";  
	}
	
	@RequestMapping(value = "/{wcode}/join", method = RequestMethod.GET)
	public String joinProject(Model model, @PathVariable("wcode") String wcode) throws Exception {
		/* 구글 로그인 인증을 위한 코드 */
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		/* 구글 로그인 인증 url 주소를 받아 넘겨준다. */
		System.out.println("/, url : " + url);
		model.addAttribute("googleSignIn", url);
		
		WorkspaceVO vo = workService.selectOne(wcode);
		model.addAttribute("wvo", vo); 
		 
		return "user/taskJoin";   
	} 
	
	@RequestMapping(value = "/{wcode}/workspace", method = RequestMethod.GET)
	public String workspaceManager(Model model,@ModelAttribute("memType") int memType,@PathVariable String wcode) throws Exception{
		
		List<MemberVO> workMembers = memService.selectListByWcode(wcode);
		List<InviteVO> inviteMembers = inviteService.selectListBywcode(wcode);
		 
		model.addAttribute("workMembers",workMembers);
		model.addAttribute("inviteMembers",inviteMembers);
		
		return "task/workspaceManagement";
	}
	
	@RequestMapping(value = "/{wcode}/project/{pno}", method = RequestMethod.GET)
	public String project(HttpSession session,Model model,@PathVariable("pno") int pno,@PathVariable("wcode") String wcode) throws Exception{
		Object object = session.getAttribute("login");
		LoginDTO login = (LoginDTO)object;  
		MemberVO vo = new MemberVO();
		vo.setUno(login.getUno());
		vo.setWcode(wcode);
		 
		List<MemberVO> workMembers = memService.selectListByWcode(wcode);
		MemberVO loginMem = memService.selectOneUnoAndwcode(vo);
		 
		model.addAttribute("workMembers",workMembers);
		model.addAttribute("loginMem",loginMem); 
		 
		return "task/project_task_management"; 
	}
	 
}  
