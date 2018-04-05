package com.dgit.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dgit.domain.InviteVO;
import com.dgit.domain.LoginDTO;
import com.dgit.domain.MemberVO;
import com.dgit.grade.MemberGrade;
import com.dgit.service.InviteService;
import com.dgit.service.MemberService;
import com.dgit.service.WorkspaceService;

public class TaskJoinInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(TaskJoinInterceptor.class);
	
	@Autowired
	private InviteService inviteService;
	
	@Autowired
	private MemberService memService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("[TaskJoinInterceptor] postHandle=======================");
		String[] uri = request.getRequestURI().split("/");
		String wcode = uri[3];
		
		HttpSession session = request.getSession();
		Object object = session.getAttribute("login");
		//로그인 여부 확인후 로그인이 되지않으면 dest에 경로 저장
		if(object == null){
			logger.info("[TaskJoinInterceptor] save Dest");
			saveDest(request); // 실제로 가야하는 경로를 받아서 저장한다
		}else{
			LoginDTO login = (LoginDTO)object; 
			InviteVO vo = new InviteVO();
			vo.setWcode(wcode);
			vo.setInvitee(login.getEmail());

			MemberVO tempVO = new MemberVO();
			tempVO.setUno(login.getUno());
			tempVO.setWcode(wcode);
			MemberVO memVo = memService.selectOneUnoAndwcode(tempVO);
			
			//로그인 한 아이디가 이미 멤버로 등록 되어있으면 해당 워크스페이스로 이동.
			if(memVo != null){
				response.sendRedirect(request.getContextPath()+"/task/"+wcode); 
				return false;
			}
			
			List<InviteVO> list= inviteService.selectListBywcodeAndInvitee(vo);
			
			MemberVO memVO = new MemberVO();
			memVO.setWcode(wcode);
			memVO.setUno(login.getUno());
			//초대된 이메일인지 확인 , 초대된 이메일 경우 일반멤버 (1) , 아닐 경우 대기멤버(2)로 멤버에 저장
			if(list.size() > 0){
				memVO.setMemGrade(1);
			}else{
				memVO.setMemGrade(2);
			}
			
			memService.insert(memVO);
			response.sendRedirect(request.getContextPath()+"/task/"+wcode); 
			return false;
		}
		  
		return true;//controller로 이동 o
	}
	
	private void saveDest(HttpServletRequest req){
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		
		if(query == null || query.equals("null")){
			query = "";
		}else{  
			query = "?" + query;  
		}
		  
		if(req.getMethod().equals("GET")){
			logger.info("dest : " + (uri+query));
			req.getSession().setAttribute("dest", uri+query); 
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("[TaskJoinInterceptor] postHandle=======================");
	}
	
}
