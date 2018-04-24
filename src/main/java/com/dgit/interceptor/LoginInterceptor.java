package com.dgit.interceptor;

import java.util.List;

import javax.servlet.RequestDispatcher;
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
import com.dgit.domain.UserVO;
import com.dgit.service.MemberService;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Autowired
	private MemberService memService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("postHandle=======================");

		HttpSession session = request.getSession();  
		String res = (String) modelAndView.getModel().get("res");
		//로그인 에러 확인
		if(res!=null){
			if(res.equals("notExtistId")){
				request.setAttribute("res", res);
				response.sendRedirect("login?res="+res);
				return ;
			}else if(res.equals("wrongPass")){
				request.setAttribute("res", res);
				response.sendRedirect("login?res="+res);
				return ;  
			}    
		}
		
		Object object = modelAndView.getModel().get("loginDto");
	
		if(object != null){  
			LoginDTO dto = (LoginDTO)object;
			logger.info("useremail : " + dto.getEmail()); 
			logger.info("userename : " + dto.getUsername()); 
			logger.info("photoPath : " + dto.getPhotoPath()); 
			session.setAttribute("login", dto); 
			List<MemberVO> memList = memService.selectListByUno(dto.getUno());
			MemberVO mem = memList.get(0);
			String wcode = mem.getWcode();
			
			Object dest = session.getAttribute("dest");
			String path = (dest != null) ? (String) dest : request.getContextPath()+"/task/" + wcode;
			session.removeAttribute("dest"); 
			logger.info("dest : " + path);
			response.sendRedirect(path);//home controller
		}				
	}  
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("preHandle=======================");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("login") != null){
			logger.info("이전 로그인 정보 삭제");
			session.removeAttribute("login");
		}
		
		return true;
	}
}
