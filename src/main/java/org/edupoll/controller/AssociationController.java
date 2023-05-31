package org.edupoll.controller;

import java.util.List;

import org.edupoll.model.entity.Avatar;
import org.edupoll.model.entity.User;
import org.edupoll.model.entity.UserDetail;
import org.edupoll.repository.AvatarRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AssociationController {
	@Autowired
	
	UserRepository userRepository;
	
	@Autowired
	AvatarRepository avatarRepository;
	
	@GetMapping("/assoc/01")
	
	public String assoc01Handle(String userId) {
		
		User found = userRepository.findById(userId).orElse(null);
		System.out.println(found);
		
		UserDetail detail = found.getUserDetail();
		System.out.println("detail = " + detail.toString());
		
		return "index";
	}
	
	//역방향으로 관계를 설정한 것을 확인하는 메서드
	@GetMapping("/assoc/02")
	public String assoc02Handle(String avatarId) {
		
		if(avatarRepository.findById(avatarId).isPresent()) {
			Avatar ava=avatarRepository.findById(avatarId).get();
			List<UserDetail> details = ava.getDetails();
			for(UserDetail user : details) {
				
				System.out.println("==>" + user.getUser().getId());
				
			}
		}else {
			System.out.println("Not found");
		}
		return "index";
		
	}
	
	
}
