package org.edupoll.Service;

import java.util.Optional;

import org.edupoll.model.dto.request.LoginRequestData;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.Reply;
import org.edupoll.model.entity.User;
import org.edupoll.model.entity.UserDetail;
import org.edupoll.repository.AttendanceRepository;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.ReplyRepository;
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
	
	@Autowired
	MoimRepository moimRepository;
	
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	ReplyRepository replyRepository;
	

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

	// 회원 상세정보를 수정/변경 처리할 서비스 메서드
	public boolean modifySpecifiUserDetail(String userId, UserDetail detail) {
		// 1. 특정 유저가 존재하는지 확인
		if (userRepository.findById(userId).isEmpty())
			return false;

		// 2.UserDetail 저장하고
		User foundUser = userRepository.findById(userId).get();
		if (foundUser.getUserDetail() != null)
			detail.setIdx(foundUser.getUserDetail().getIdx());

		UserDetail saved = userDetailRepository.save(detail);
		// 3. 특정 유저의 detail_idx에 방금 저장하며 부여받은 id 값을 설정해서 update
		foundUser.setUserDetail(saved);
		userRepository.save(foundUser);

		return true;

	}

	public UserDetail findSpecifiSavedDetail(String logonId) {

//		UserDetail userDetail = userRepository.findById(logonId).get().getUserDetail().getIdx();
//		if(userDtail==null){
//			return null;
//		}
//		return userDetailRepository.findById(userDetail.getIdx()).orElse(null);

		return userRepository.findById(logonId).get().getUserDetail();
	}

	@Transactional
	public boolean removeToUser(String logonId) {

		if (userRepository.findById(logonId).isPresent()) {
			return false;
		}

		User found = userRepository.findById(logonId).get();
		UserDetail userDetail = found.getUserDetail();
	

		userRepository.delete(found);
		userDetailRepository.delete(found.getUserDetail());
		return true;

	}
	
	
	public User findSpecifiUserById(String targetId) {
		
		return userRepository.findById(targetId).orElse(null);
	}


	
}
