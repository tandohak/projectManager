package com.dgit.controller;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dgit.domain.EmailAuthVO;
import com.dgit.domain.LoginDTO;
import com.dgit.domain.UserVO;
import com.dgit.persistence.EmailAuthDAO;
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
	@Autowired
	private EmailAuthDAO emailDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(Model model) {
		/* 구글 로그인 인증을 위한 코드 */
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		/* 구글 로그인 인증 url 주소를 받아 넘겨준다. */
		System.out.println("/, url : " + url);
		model.addAttribute("googleSignIn", url);
		return "user/login";
	} 

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPost(LoginDTO dto, Model model) throws Exception {
		logger.info("[loginPOST] ---------------- ");
		logger.info(dto.toString());

		/*
		 * MemberVO vo = service.readWithPw(dto.getUserid(), dto.getUserpw());
		 * 
		 * if(vo == null){ logger.info("user 없음........"); logger.info(
		 * "loginPOST return........"); return; }
		 * 
		 * dto.setUsername(vo.getUsername()); dto.setUserpw("");
		 * 
		 * model.addAttribute("loginDto",dto);
		 */
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGet(String logout, HttpSession session) throws Exception {
		logger.info("[logoutGet] ---------------- ");

		if (logout == null) {
			logger.info("user 없음........");
			logger.info("loginPOST return........");
			session.invalidate();
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinGet(Model model) {
		/* 구글 로그인 인증을 위한 코드 */
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		/* 구글 로그인 인증 url 주소를 받아 넘겨준다. */
		System.out.println("/, url : " + url);
		model.addAttribute("googleSignIn", url);
		return "user/join";
	} 
	
	
	@RequestMapping("/googleSignInCallback")
	public String doSessionAssignActionPage(HttpSession session, String code,Model model) throws Exception {
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

		UserVO tempUser = service.selectOneByEmail(person.getAccountEmail());
		
		if (tempUser != null) {
			session.setAttribute("user", tempUser);
			
			return "redirect:/taskManagement/project";
		} else {
			UserVO user = new UserVO();
			user.setEmail(person.getAccountEmail());
			user.setFirstName(person.getFamilyName());
			user.setLastName(person.getGivenName());
			model.addAttribute("user", user);
			
			return "user/join/step1";
		}
		
		/*
		 * System.out.println("디스플레이 이름"+person.getDisplayName());
		 * System.out.println("어카운트 이메일"+person.getAccountEmail());
		 * System.out.println("??"+person.getAboutMe());
		 * System.out.println("eTag"+person.getEtag()); 
		 * System.out.println("이름 :"+person.getGivenName()); 
		 * System.out.println("성 :"+person.getFamilyName());
		 * System.out.println("성별 :"+person.getGender());
		 */
	}
	@RequestMapping("/join/step1")
	public String joinStep1(String user_email,String key,UserVO user,Model model) throws Exception{  

		
		if(!key.equals("")){
			UserVO userTemp = new UserVO();
			EmailAuthVO vo = new EmailAuthVO(user_email, key);
			EmailAuthVO temp = emailDao.selectOne(vo);
			
			if(temp==null){
				return "redirect:/user/join";
			} 
			
			userTemp.setEmail(user_email);
			model.addAttribute("user",userTemp);
		}else{
			model.addAttribute("user",user);
		}
		
		return "user/join/step1";
	}

}
