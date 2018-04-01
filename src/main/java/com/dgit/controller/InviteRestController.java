package com.dgit.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dgit.domain.EmailAuthVO;
import com.dgit.domain.InviteVO;
import com.dgit.persistence.EmailAuthDAO;
import com.dgit.util.MailHandler;
import com.dgit.util.TempKey;

@RestController
@RequestMapping("/invite/*")
public class InviteRestController {
	@Inject
	private JavaMailSender mailSender;

	@Autowired
	private EmailAuthDAO dao;
	private static final Logger logger = LoggerFactory.getLogger(InviteRestController.class);

	@RequestMapping(value = "/emailAuth", method = RequestMethod.POST)
	public ResponseEntity<String> emailAuth(Model model, String email) {
		ResponseEntity<String> entity = null;
		
		try {
			String key = new TempKey().getKey(50, false); // 인증키 생성
			
			EmailAuthVO vo = new EmailAuthVO(email, key);
			
			dao.insert(vo);// 인증키 DB저장
			
			MailHandler sendMail = new MailHandler(mailSender);
			sendMail.setSubject("[TASK MANAGER 서비스 이메일 인증]");
			sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
					.append("<a href='http://localhost:8080/projectManager/user/join/step1?user_email=").append(email)
					.append("&key=").append(key).append("' target='_blenk'>이메일 인증 확인</a>").toString());
			sendMail.setFrom("taskmanager0909", "테스트");
			sendMail.setTo(email);
			sendMail.send();
			entity = new ResponseEntity<>(email,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
		} 
		
		return entity;
	}
	
	@RequestMapping(value = "/inviteTeam", method = RequestMethod.GET)
	public ResponseEntity<String> inviteTeam(Model model, String email) {
		ResponseEntity<String> entity = null;
		
		try {
			InviteVO vo = new InviteVO();
			
			entity = new ResponseEntity<>(email,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
		} 
		
		return entity;
	}

}
