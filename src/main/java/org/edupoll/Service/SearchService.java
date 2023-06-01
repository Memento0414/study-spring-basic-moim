package org.edupoll.Service;

import java.util.ArrayList;
import java.util.List;

import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.User;
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
	
	
	
	
//	public List<UserResponseData> searchKeyword(String keyword) {
//		
//		List<User> findKeyword= userRepository.findByIdContainingOrNickContainingAllIgnoreCase(keyword, keyword);
//		
//		List<UserResponseData> trans = new ArrayList<>();
//		
//		for(User user : findKeyword) {
//			
//			trans.add(new UserResponseData(user));
//		}
//		
//		return findKeyword.stream().map(t -> new UserResponseData(t)).toList();
//	}
	
	//유저 혹은 닉 으로 검색할 사용
	public List<User> findAllUser (String keyword, int page) {
		
		List<User> findUser = userRepository.findByIdContainingOrNickContainingAllIgnoreCase
				(keyword, keyword, PageRequest.of(page-1, 12, Sort.by(Direction.ASC, "id")));
		
		return findUser;
		
	}
	
	public List<String> findByUserPageCount (int page, String keyword) {
			
		List<User> list = userRepository.findByIdContainingOrNickContainingAllIgnoreCase(keyword, keyword);
		
		
		long totalData = userRepository.count();
		System.out.println("totaldata = " + totalData);
		
		List<String> pages = new ArrayList<>();
		
		for(int i = 1; i < totalData / 12 + (totalData % 12 > 0 ? 1 : 0); i++) {
			pages.add(String.valueOf(i));
		}
		return pages;
		
	}
	
	public List<Moim> MoimSearch(String cate) {
		
		List<Moim> found = moimRepository.findByCateContainingAllIgnoreCase(cate);
		
		return found;
	}
	
}
