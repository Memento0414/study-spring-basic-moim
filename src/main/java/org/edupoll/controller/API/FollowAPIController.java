package org.edupoll.controller.API;

import org.edupoll.Service.FollowService;
import org.edupoll.model.dto.response.FollowResponseData;
import org.springframework.beans.factory.annotation.Autowired;
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
	public FollowResponseData addFollowHandle(@SessionAttribute(name="logonId") String ownerId, @RequestParam String target) {
		
		
		return followService.following(ownerId, target);
		
	}
	
	@DeleteMapping
	public void cancelFollowHandle(@SessionAttribute(name="logonId") String ownerId, @RequestParam String target) {
	
		followService.cancelFollow(ownerId, target);
	}
	
}
