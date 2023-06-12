package org.edupoll.controller;

import org.edupoll.Service.UserService;
import org.edupoll.model.dto.request.UserJoinData;
import org.edupoll.security.support.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/join")
	public String goToJoin() {
		
		return "user/join";
	}
	
	@PostMapping("/user/join")
	public String userJoinHandle(@Valid UserJoinData joinData, BindingResult result, Model model) {
		boolean rst = userService.create(joinData);
		logger.debug("userJoinHandle's result : {} ", rst);
		
		if(result.hasErrors()) {
			model.addAttribute("error", true);
			return "user/join";
			
		} else {

			if (rst) {
				return "redirect:/user/login";
			} else {
				
				model.addAttribute("error", true);
				return "user/join";
			}
		}
	}
	
	@GetMapping("/user/login")
	public String goToLogin() {
		
		return "user/login";
	}
	/*
	@PostMapping("/user/login")
	public String userLoginHandle(LoginRequestData data, HttpSession session ,Model model) {
		
		boolean result = userService.isValidUser(data);
		logger.debug("UserLoginHandle's result : {} ", result);
		
		
		if(result) {
			
			session.setAttribute("logonId", data.getLoginId());
			return "index";
			
		} else {
			
			model.addAttribute("error", true);
			return "user/login";
		}
		
	}
	
	
	@GetMapping("/user/logout")
	public String userLogoutHandle(HttpSession session) {
		
		session.invalidate();
	
		return "index";
	}
	*/
	@GetMapping("/user/delete")
	public String userDeleteHandle(@AuthenticationPrincipal Account account, HttpSession session) {

		boolean rst = userService.removeToUser(account.getUsername());
		logger.debug("userDeleteHandle's result : {} ", rst);
		if (rst) {
			session.invalidate();
			return "redirect:/user/login";
		} else {
			
			return "prvate/modify";
		}
	}
}
