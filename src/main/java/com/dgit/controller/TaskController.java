package com.dgit.controller;

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

import com.dgit.domain.LoginDTO;
import com.dgit.domain.MemberVO;
import com.dgit.domain.WorkspaceVO;
import com.dgit.service.MemberService;
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
	public String project(Model model, @PathVariable("wcode") String wcode,HttpSession session) throws Exception {
		WorkspaceVO vo = workService.selectOne(wcode);
		
		Object object = session.getAttribute("login");
		LoginDTO login = (LoginDTO)object;
		
		List<MemberVO> memList = memService.selectListByUnoJoinWorkspace(login.getUno());

		MemberVO tempVO = new MemberVO();
		tempVO.setUno(login.getUno());
		tempVO.setWcode(wcode);
		MemberVO memVo = memService.selectOneUnoAndwcode(tempVO);
		
		model.addAttribute("workVO", vo);
		model.addAttribute("memList",memList); 
		model.addAttribute("memVo",memVo); 
		
		return "task/project";  
	}
	
	@RequestMapping(value = "/{wcode}/join", method = RequestMethod.GET)
	public String joinProject(Model model, @PathVariable("wcode") String wcode, @ModelAttribute("email") String email) throws Exception {
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

}
