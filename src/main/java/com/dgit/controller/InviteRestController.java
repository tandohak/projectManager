package com.dgit.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dgit.domain.EmailAuthVO;
import com.dgit.domain.InviteVO;
import com.dgit.persistence.EmailAuthDAO;
import com.dgit.service.InviteService;
import com.dgit.util.MailHandler;
import com.dgit.util.TempKey;

@RestController
@RequestMapping("/invite/*")
public class InviteRestController {
	@Inject
	private JavaMailSender mailSender;

	@Autowired
	private EmailAuthDAO dao;

	@Autowired
	private InviteService inviteService;
	
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
	
	@RequestMapping(value = "/inviteTeam", method = RequestMethod.POST)
	public ResponseEntity<String> inviteTeam(@RequestBody HashMap<String, Object> map) {
		ResponseEntity<String> entity = null;
		
		List<String> emails = (List<String>) map.get("emails");
		String inviter = (String) map.get("inviter");
		String wcode = (String) map.get("wcode");
		String maker = (String) map.get("maker");
		
		try {
			InviteVO vo = new InviteVO();
			vo.setInviter(inviter);
			vo.setWcode(wcode);
			
			for (String email : emails) {
				vo.setInvitee(email);
				inviteService.insert(vo); 
			}
			  
			for (String email : emails) {
				MailHandler sendMail = new MailHandler(mailSender);
				sendMail.setSubject("[초대 : TASK MANAGER]"+ maker +"님의 워크스페이스에 가입하세요.");
				sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
						.append("<a href='http://localhost:8080/projectManager/task/"+wcode)
						.append("/join?email="+email)
						.append("' target='_blenk'>이메일 인증 확인</a>").toString());
				sendMail.setFrom("taskmanager0909", "테스트");
				sendMail.setTo(email);
				sendMail.send();
			}
			entity = new ResponseEntity<>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
		} 
		
		return entity; 
	}
	
	@RequestMapping(value="delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delInvite(String invitee){
		ResponseEntity<String> entity = null;
		logger.info("invitee-" + invitee);
		try {  
			inviteService.delete(invitee);
			entity = new ResponseEntity<>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
