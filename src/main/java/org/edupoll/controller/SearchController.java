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
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class SearchController {
	Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired	SearchService searchService;
	

	@GetMapping("/search")
	public String ShowSearchHandle(@RequestParam(required = false)String keyword, @RequestParam(defaultValue = "1")int page, @SessionAttribute(required = false) String logonId ,Model model) {

		if (keyword == null) {

			return "/search/form";
		} else {
			if(logonId == null) {
				List<UserResponseData> findUser = searchService.findAllUser(keyword, page);
				model.addAttribute("findUser", findUser);
				
				List<String> pages = searchService.findAllUserPageCount(page, keyword);
				model.addAttribute("pages", pages);
				
			} else { 
				model.addAttribute("findUser", searchService.getUserMatchedKeywordBySpecificUser(keyword, logonId, page));
				
				List<String> pages = searchService.findAllUserPageCount(page, keyword);
				model.addAttribute("pages", pages);
			}

			model.addAttribute("keyword", keyword);
			return "search/result";
		}
	}
	

}
