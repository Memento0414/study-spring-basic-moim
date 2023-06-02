package org.edupoll.controller;

import java.util.List;

import org.edupoll.model.entity.Attendance;
import org.edupoll.model.entity.Avatar;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.User;
import org.edupoll.model.entity.UserDetail;
import org.edupoll.repository.AttendanceRepository;
import org.edupoll.repository.AvatarRepository;
import org.edupoll.repository.MoimRepository;
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
	
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	MoimRepository moimRepository;
	
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
	
	@GetMapping("/assoc/03")
	public String assoc03Handle() {
		User found =userRepository.findById("sadako").get();
		System.out.println(found.getId());
		List<Moim> attends = found.getAttendMoims();
		
		for(Moim m : attends) {
			System.out.println(m.getId() + " / " + m.getTitle());
		}
		
		return "index";
	}
	
	@GetMapping("/assoc/04")
	public String assoc04Handle() {
		
		Moim moim = moimRepository.findById("").get();
		for(Attendance a : moim.getAttendance()) {
			
			System.out.println(a.getUser().getId());
		}
		List<User> users = moim.getAttendUsers();
		
		for(User one : users) {
			System.out.println(one.getId());
		}
		
		return "index";
	}
	
}
