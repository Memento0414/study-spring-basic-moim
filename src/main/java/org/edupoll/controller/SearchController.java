package org.edupoll.controller;

import java.util.List;

import org.edupoll.Service.SearchService;
import org.edupoll.model.dto.response.UserResponseData;
import org.edupoll.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
	Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired	SearchService searchService;
	
//	
//	@GetMapping("/search")
//	public String searchHandle(String keyword, Model model) {
//		if(keyword != null) {
//		List<UserResponseData> list = searchService.searchKeyword(keyword);
//		logger.debug("searchHandle's result : {} ", keyword);
//		model.addAttribute("list", list);
//		model.addAttribute("keyword", keyword);
//		
//		
//		}
//		return "search";
//	}
	


	@GetMapping("/search")
	public String ShowSearchHandle(@RequestParam(required = false)String keyword, @RequestParam(defaultValue = "1")int page, Model model) {
			
			if(keyword == null) {
				
				return "/search/form";
			}
		
			List<User> findUser = searchService.findAllUser(keyword, page);
			model.addAttribute("findUser",findUser);
			model.addAttribute("keyword", keyword);
			
			List<String> pages = searchService.findAllUserPageCount(page, keyword);
			model.addAttribute("pages", pages);
			
			

			return "search/result";

	}
	

}
