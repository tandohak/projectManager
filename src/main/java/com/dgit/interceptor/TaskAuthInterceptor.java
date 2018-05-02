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

import com.dgit.domain.LoginDTO;
import com.dgit.domain.MemberVO;
import com.dgit.domain.WorkspaceVO;
import com.dgit.service.MemberService;
import com.dgit.service.WorkspaceService;

public class TaskAuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(TaskAuthInterceptor.class);

	@Autowired
	private WorkspaceService workService;

	@Autowired
	private MemberService memService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("[TaskAuthInterceptor] preHandle=======================");
		
		String[] uri = request.getRequestURI().split("/");
		String wcode = uri[3];
		logger.info("[TaskAuthInterceptor] wcode -" + wcode);
		WorkspaceVO vo = workService.selectOne(wcode);

		// 존재하는 워크스페이스인지 검사후 존재하지 않을경우 error404 페이지로 이동
		if (vo == null) {
			response.sendRedirect(request.getContextPath() + "/err/404");
			return false;
		}
		HttpSession session = request.getSession();
		LoginDTO login = (LoginDTO)session.getAttribute("login");
		logger.info("[TaskAuthInterceptor] LoginDTO -" + login.toString());

		Object object = session.getAttribute("login");
		
		List<MemberVO> memList = memService.selectListByUnoJoinWorkspace(login.getUno());
		logger.info("[TaskAuthInterceptor]  memList size -" + memList.size());
		
		MemberVO tempVO = new MemberVO();
		tempVO.setUno(login.getUno());
		tempVO.setWcode(wcode);
		MemberVO memVo = memService.selectOneUnoAndwcode(tempVO);
		logger.info("[TaskAuthInterceptor] 로그인 MemberVO -" + memList.size());
		// memVo가 없을경우 에러페이지로 이동
		if(memVo == null){
			response.sendRedirect(request.getContextPath()+"/err/unauthorizedPage");
		}
		
		request.setAttribute("workVO", vo); 
		request.setAttribute("memList",memList); 
		request.setAttribute("memVo",memVo); 
		
		return true;// controller로 이동 o
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("[TaskAuthInterceptor] postHandle=======================");
	}

}
