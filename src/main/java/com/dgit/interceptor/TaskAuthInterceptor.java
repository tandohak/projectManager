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

		WorkspaceVO vo = workService.selectOne(wcode);

		// 존재하는 워크스페이스인지 검사후 존재하지 않을경우 error404 페이지로 이동
		if (vo == null) {
			response.sendRedirect(request.getContextPath() + "/err/404");
			return false;
		}
		HttpSession session = request.getSession();
		LoginDTO login = (LoginDTO)session.getAttribute("login");

		List<MemberVO> memList = memService.selectListByUnoJoinWorkspace(login.getUno());

		// 주소로 접속한 wcode와 로그인한 아이디의 wcode를 비교하여
		// 자기자신의 워크스페이스인지 확인하기 위한 코드  
		Boolean chckWcode = false;   
		logger.info("memlist - " + memList.size());  
		for (MemberVO mvo : memList) {
			if (mvo.getWcode().equals(wcode)) {  
				chckWcode = true;  
				break;  
			} 
			logger.info("chckwcode"+chckWcode);
		} 
		
		logger.info("chckwcode- chck"+chckWcode);
		// chckWcode가 false 일경우 에러페이지로 이동
		if (!chckWcode) {
			response.sendRedirect(request.getContextPath()+"/err/unauthorizedPage");
		}
    
		return true;// controller로 이동 o
	}

	private void saveDest(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String query = req.getQueryString();

		if (query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}

		if (req.getMethod().equals("GET")) {
			logger.info("dest : " + (uri + query));
			req.getSession().setAttribute("dest", uri + query);
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("[TaskAuthInterceptor] postHandle=======================");
	}

}
