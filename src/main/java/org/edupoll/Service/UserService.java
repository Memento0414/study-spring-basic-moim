package org.edupoll.Service;

import java.util.Optional;

import org.edupoll.model.dto.LoginRequestData;
import org.edupoll.model.entity.User;
import org.edupoll.model.entity.UserDetail;
import org.edupoll.repository.UserDetailRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	UserDetailRepository userDetailRepository;
	
	
	


	// 회원 가입을 처리할 서비스 메서드
	public boolean create(User user) {

		if (userRepository.findById(user.getId()).isEmpty()) {

			userRepository.save(user);
			return true;
		} else {
			return false;
		}

	}
	// 로그인 처리를 하기 위해 사용될 메서드

	public boolean isValidUser(LoginRequestData data) {

		Optional<User> optional = userRepository.findById(data.getLoginId());

		if (optional.isPresent()) {
			String savePass = optional.get().getPass();
			return savePass.equals(data.getLoginPass());
		}
		return false;

	}

	//회원 상세정보를 수정/변경 처리할 서비스 메서드
	public boolean modifySpecifiUserDetail(String userId, UserDetail detail) {
		//1. 특정 유저가 존재하는지 확인
		if(userRepository.findById(userId).isEmpty()) 
			return false;
		
		//2.UserDetail 저장하고
		User foundUser = userRepository.findById(userId).get();
		if(foundUser.getUserDetailIdx() != null)
			detail.setIdx(foundUser.getUserDetailIdx());
		
		UserDetail saved = userDetailRepository.save(detail);
		//3. 특정 유저의 detail_idx에 방금 저장하며 부여받은 id 값을 설정해서 update
		foundUser.setUserDetailIdx(saved.getIdx());
		userRepository.save(foundUser);
		
		return true;
		
	}


	public UserDetail findSpecifiSavedDetail(String logonId) {
		//logonId로 유저 정보 찾아서 그 유저의 detail_idx 찾아서
		Integer detailIdx = userRepository.findById(logonId).get().getUserDetailIdx();
		if(detailIdx == null)
			return null;

		// 유저 상세 정보 찾아서 리턴
		return userDetailRepository.findById(detailIdx).orElse(null);
		
	}
	
	@Transactional
	public boolean removeToUser(String logonId) {

		User found = userRepository.findById(logonId).get();
		
		if(found.getUserDetailIdx() != null) {
			userRepository.deleteById(logonId);
			userDetailRepository.deleteById(found.getUserDetailIdx());
			return true;
			
		} else {
			
			return false;
		}

	}

}
