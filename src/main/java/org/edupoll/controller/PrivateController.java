package org.edupoll.controller;

import org.edupoll.Service.UserService;
import org.edupoll.model.entity.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public String showPrivateModifyForm(@SessionAttribute String logonId, Model model) {
		UserDetail savedDetail = userService.findSpecifiSavedDetail(logonId);
		
		logger.debug("showPrivateModifyForm's result : {} ", savedDetail);
		
		model.addAttribute("savedDetail", savedDetail);
		
		return "private/modify-form";
	}
	
	
	
	@PostMapping("/private/modify")
	public String privateModifyHandle(@SessionAttribute String logonId, UserDetail detail, Model model) {
		
		System.out.println("logonId" + logonId);
		boolean rst =userService.modifySpecifiUserDetail(logonId, detail);
		
		logger.debug("privateModifyHandle's result : {} ", rst);
		
		return "redirect:/private/modify";
	}
	
}
