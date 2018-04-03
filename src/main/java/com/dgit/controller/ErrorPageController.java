package com.dgit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@RequestMapping("/err/*")
public class ErrorPageController {

	@RequestMapping("/404")
	public String err404() {
		return "error/404";
	}
}
