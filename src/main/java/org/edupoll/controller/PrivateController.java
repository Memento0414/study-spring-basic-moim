package org.edupoll.controller;

import org.edupoll.Service.UserService;
import org.edupoll.model.entity.UserDetail;
import org.edupoll.security.support.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller

public class PrivateController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	UserService userService;
	
	@GetMapping("/private/modify")
	public String showPrivateModifyForm(@AuthenticationPrincipal Account account, Model model) {
		UserDetail savedDetail = userService.findSpecifiSavedDetail(account.getUsername());
		
		logger.debug("showPrivateModifyForm's result : {} ", savedDetail);
		
		model.addAttribute("savedDetail", savedDetail);
		
		return "private/modify-form";
	}
	
	
	
	@PostMapping("/private/modify")
	public String privateModifyHandle(@AuthenticationPrincipal Account account, UserDetail detail, Model model) {
		
		boolean rst =userService.modifySpecifiUserDetail(account.getUsername(), detail);
		
		logger.debug("privateModifyHandle's result : {} ", rst);
		
		return "redirect:/private/modify";
	}
	
	//프로필 뷰에 보이는 정보 처리 
	@GetMapping("/private")
	public String showPrivateInfoView(@AuthenticationPrincipal Account account, Model model) {
		
		 model.addAttribute("user", userService.findSpecifiUserById(account.getUsername()));
		 return "private/default";
	}
}
