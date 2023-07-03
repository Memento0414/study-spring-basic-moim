package org.edupoll.controller.API;

import org.edupoll.model.dto.response.FollowResponseData;
import org.edupoll.security.support.Account;
import org.edupoll.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api/follow")
public class FollowAPIController {
	
	@Autowired
	FollowService followService;
	
	@PostMapping
	public FollowResponseData addFollowHandle(@AuthenticationPrincipal Account account, @RequestParam String target) {
		
		
		return followService.following(account.getUsername(), target);
		
	}
	
	@DeleteMapping
	public FollowResponseData cancelFollowHandle(@AuthenticationPrincipal Account account, @RequestParam String target) {
	
		return followService.cancelFollow(account.getUsername(), target);
	}
	
}
