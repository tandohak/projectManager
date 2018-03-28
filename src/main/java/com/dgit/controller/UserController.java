package com.dgit.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dgit.domain.LoginDTO;
import com.dgit.domain.UserVO;
import com.dgit.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {       
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;

	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;
	
	@Autowired
	private UserService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGet() {
	}

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPost(LoginDTO dto,Model model) throws Exception {
		logger.info("[loginPOST] ---------------- ");
		logger.info(dto.toString());

		/*MemberVO vo = service.readWithPw(dto.getUserid(), dto.getUserpw());
			
		if(vo == null){
			logger.info("user 없음........");
			logger.info("loginPOST return........");
			return;
		}
		
		dto.setUsername(vo.getUsername());
		dto.setUserpw("");
		
		model.addAttribute("loginDto",dto);*/
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logoutGet(String logout,HttpSession session) throws Exception {
		logger.info("[logoutGet] ---------------- ");
		
		if(logout == null){
			logger.info("user 없음........");
			logger.info("loginPOST return........");
			session.invalidate();
		}
		  
		return "redirect:/";
	}
	
	@RequestMapping("/googleSignInCallback")
	public String doSessionAssignActionPage(HttpSession session, String code) {
		System.out.println("/member/googleSignInCallback");
		System.out.println(code);
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, googleOAuth2Parameters.getRedirectUri(),
				null);

		String accessToken = accessGrant.getAccessToken();
		Long expireTime = accessGrant.getExpireTime();
		if (expireTime != null && expireTime < System.currentTimeMillis()) {
			accessToken = accessGrant.getRefreshToken();  
			System.out.printf("accessToken is expired. refresh token = {}", accessToken);
		}
		Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
		Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();

		PlusOperations plusOperations = google.plusOperations();
		Person person = plusOperations.getGoogleProfile();  
		
		UserVO user = new UserVO();
		user.setEmail(person.getAccountEmail());
		user.setFirstName(person.getFamilyName());
		user.setLastName(person.getGivenName());
		session.setAttribute("user", user);

		System.out.println("디스플레이 이름"+person.getDisplayName());
		System.out.println("어카운트 이메일"+person.getAccountEmail());
//		System.out.println("??"+person.getAboutMe());
		System.out.println("eTag"+person.getEtag()); 
		System.out.println("이름 :"+person.getGivenName());
		System.out.println("성 :"+person.getFamilyName());
		System.out.println("성별 :"+person.getGender());
		 
		return "redirect:/";

	}

}
