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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class MoimService {

	@Autowired
	MoimRepository moimRepository;
	
	@Autowired
	UserRepository userRepository;
	
	//모임 생성
	public String createMoim(Moim moim, String logonId) {
		User found= userRepository.findById(logonId).get();// 로그온 상태라면 무조건 있는 데이터
			/*
			 * User user = new User();
			 * user.setId(logonId);
			 * user.setManager(user);
			 * */
			moim.setCurrentPerson(1);
			moim.setManager(found);
			Moim saved = moimRepository.save(moim);
			
			return saved.getId();
	}
	
	//특정 Id의 모임정보 불러오기용 서비스 메서드
	public Moim findMoim(String logonId){

		return  moimRepository.findById(logonId).orElse(null);
	}
	
	//모임 전체글 페이징처리
	
	@Transactional(readOnly = true)
	public List<Moim> findAllMoim(int page){
		PageRequest pageRequest = PageRequest.of(page-1, 12, Sort.by(Direction.ASC, "targetDate"));
		
		return  moimRepository.findAll(pageRequest).toList();
	}
	
	
	public List<String> MoimPaggingCount (int page){
		
		long totalPage = moimRepository.count();
		
		List<String> pages = new ArrayList<>();
		for(int i = 1; i <= totalPage/12 + (totalPage% 12> 0 ? 1: 0); i++) {
			pages.add(String.valueOf(i));
			
		}
		return pages;
	}
	

}
