package org.edupoll.Service;

import java.util.Date;

import org.edupoll.model.dto.response.FollowResponseData;
import org.edupoll.model.entity.Follow;
import org.edupoll.repository.FollowRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

	@Autowired
	FollowRepository followRepository;
	
	@Autowired
	UserRepository userRepository;
	
	//팔로우 신청할 때 처리하는 메서드
	public FollowResponseData following(String ownerId, String userId) {
		
		if(followRepository.existsByOwnerIdIsAndTargetIdIs(ownerId, userId)) {
	
			return new FollowResponseData(false);
		} else {
			Follow follow = new Follow();
			follow.setOwner(userRepository.findById(ownerId).get());
			follow.setTarget(userRepository.findById(userId).get());
			follow.setCreated(new Date());
			followRepository.save(follow);
			return new FollowResponseData(true);
		}
	}
	
	public void cancelFollow(String ownerId, String userId) {
		
		 followRepository.deleteByOwnerIdIsAndTargetIdIs(ownerId, userId);
		
		
	}
	
	
}