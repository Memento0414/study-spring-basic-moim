package org.edupoll.service;

import java.util.ArrayList;
import java.util.List;

import org.edupoll.model.dto.response.FollowResponseData;
import org.edupoll.model.dto.response.UserResponseData;

import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.User;
import org.edupoll.repository.FollowRepository;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MoimRepository moimRepository;
	
	@Autowired
	FollowRepository followRepository;
	
	
	
	//유저 혹은 닉 으로 검색할 사용(특정 단어를 이용해서 )
	public List<UserResponseData> findAllUser (String keyword, int page) {
		
		List<User> findUser = userRepository.findByIdContainingOrNickContainingAllIgnoreCase
				(keyword, keyword, PageRequest.of(page-1, 12, Sort.by(Direction.ASC, "id")));
		List<UserResponseData> respList = new ArrayList<>();
		
		for(User user : findUser) {
			respList.add(new UserResponseData(user));
		}
		
		
		return findUser.stream().map(t-> new UserResponseData(t)).toList();
		
	}
	
	public List<String> findByUserPageCount (int page, String keyword) {
			
		List<User> list = userRepository.findByIdContainingOrNickContainingAllIgnoreCase(keyword, keyword);
		
		

		long totalData = userRepository.countByIdContainingOrNickContainingAllIgnoreCase(keyword, keyword);
		System.out.println(totalData);
		
		List<String> pages = new ArrayList<>();
		
		
		for(int i = 1; i < totalData / 12 + (totalData % 12 > 0 ? 1 : 0); i++) {
			pages.add(String.valueOf(i));
		}
		return pages;
		
	}
		
	
	public Object getUserMatchedKeywordBySpecificUser(String keyword, String logonId, int page) {
		
		List<User> list = userRepository.findByIdContainingOrNickContainingAllIgnoreCase(keyword, keyword, PageRequest.of(page-1, 12, Sort.by(Direction.ASC, "id")));
		
		List<UserResponseData> responseList = list.stream().map(t-> new UserResponseData(t)).toList();
		
		for(UserResponseData urd : responseList) {
			
			if(followRepository.existsByOwnerIdIsAndTargetIdIs(logonId, urd.getId())) {
				urd.setFollowed(true);
			}
		}
		return responseList;
	}
	
	public List<Moim> MoimSearch(String cate) {
		
		List<Moim> found = moimRepository.findByCateContainingAllIgnoreCase(cate);
		
		return found;
	}

	
	
	
	
	
}
