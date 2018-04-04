package com.dgit.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dgit.domain.EmailAuthVO;
import com.dgit.domain.LoginDTO;
import com.dgit.domain.MemberVO;
import com.dgit.domain.UserVO;
import com.dgit.persistence.EmailAuthDAO;
import com.dgit.service.MemberService;
import com.dgit.service.UserService;
import com.dgit.service.WorkspaceService;

@Controller
@RequestMapping("/user/*")
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
	@Autowired
	private WorkspaceService wrokService;
	@Autowired
	private MemberService memService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(Model model,@ModelAttribute("res") String res) {
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
		UserVO idCheck =service.selectOneByEmail(dto.getEmail());
	
		if(idCheck == null){
			logger.info("user 없음........");
			model.addAttribute("res","notExtistId");
			return;
		}
		
		UserVO vo = service.readWithPw(dto.getEmail(), dto.getPassword());

		if (vo == null) {
			logger.info("비밀 번호 틀림........");
			logger.info("loginPOST return........");
			model.addAttribute("res","wrongPass");
			return;
		}  

		dto.setUsername(vo.getFirstName() + " " + vo.getLastName());
		dto.setUno(vo.getUno());
		dto.setPassword("");

		model.addAttribute("loginDto", dto); 
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
	public String doSessionAssignActionPage(HttpServletRequest request,HttpSession session, String code, Model model) throws Exception {
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
			LoginDTO dto = new LoginDTO();   
			dto.setUno(tempUser.getUno());  
			dto.setEmail(tempUser.getEmail());
			dto.setUsername(tempUser.getFirstName()+ " " + tempUser.getLastName());
			dto.setPhotoPath(tempUser.getPhotoPath());
			session.setAttribute("login", dto);  
			
			List<MemberVO> memList = memService.selectListByUno(tempUser.getUno());
			MemberVO memVo = memList.get(0);
			String wcode = memVo.getWcode();
			Object dest = session.getAttribute("dest");
			String path = (dest != null) ? ((String) dest).substring(15) : "/task/" + wcode;
			System.out.println(path);
			session.removeAttribute("dest"); 
			return "redirect:"+path;
		} else {
			UserVO user = new UserVO();  
			user.setEmail(person.getAccountEmail());
			user.setFirstName(person.getFamilyName());
			user.setLastName(person.getGivenName());
			model.addAttribute("user", user);
			
			return "user/join/signUp";
		}

		/*
		 * System.out.println("디스플레이 이름"+person.getDisplayName());
		 * System.out.println("어카운트 이메일"+person.getAccountEmail());
		 * System.out.println("??"+person.getAboutMe());
		 * System.out.println("eTag"+person.getEtag()); System.out.println(
		 * "이름 :"+person.getGivenName()); System.out.println("성 :"
		 * +person.getFamilyName()); System.out.println("성별 :"
		 * +person.getGender());
		 */
	}

	@RequestMapping("/join/signUp")
	public String joinStep1(String user_email, String key, UserVO user, Model model, HttpSession session)
			throws Exception {
		// email에 회원가입된 유저가 존재하는지 파악
		UserVO voTemp = service.selectOneByEmail(user_email);
		if (voTemp != null) {
			session.setAttribute("user", voTemp);
			return "redirect:/user/login";
		}

		if (!key.equals("")) {
			UserVO userTemp = new UserVO();
			EmailAuthVO vo = new EmailAuthVO(user_email, key);
			EmailAuthVO temp = emailDao.selectOne(vo);

			if (temp == null) {
				return "redirect:/user/join";
			}

			userTemp.setEmail(user_email);
			model.addAttribute("user", userTemp);
			model.addAttribute("key", key);
		} else {
			model.addAttribute("user", user);
		}

		return "user/join/signUp";
	}
	
}
