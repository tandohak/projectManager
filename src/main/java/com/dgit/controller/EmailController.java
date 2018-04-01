package com.dgit.controller;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dgit.domain.EmailAuthVO;
import com.dgit.persistence.EmailAuthDAO;
import com.dgit.util.MailHandler;
import com.dgit.util.TempKey;

@Controller
public class EmailController {
	@Inject
	private JavaMailSender mailSender;

	@Autowired
	private EmailAuthDAO dao;

	@RequestMapping(value = "/emailAuth", method = RequestMethod.POST)
	public String email(Model model, String email) throws Exception {
		String key = new TempKey().getKey(50, false); // 인증키 생성

		EmailAuthVO vo = new EmailAuthVO(email, key);
		dao.insert(vo); // 인증키 DB저장

		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[TASK MANAGER 서비스 이메일 인증]");
		sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
				.append("<a href='http://localhost:8080/projectManager/user/join/signUp?user_email=").append(email)
				.append("&key=").append(key).append("' target='_blenk'>이메일 인증 확인</a>").toString());
		sendMail.setFrom("taskmanager0909", "테스트");
		sendMail.setTo(email);
		sendMail.send();

		return "redirect:/thankChooseUs?email=" + email;
	}

	@RequestMapping("/thankChooseUs")
	public String thankChooseUs(@ModelAttribute("email") String email, Model model) {
		return "user/emailTransFinish";
	}
}
