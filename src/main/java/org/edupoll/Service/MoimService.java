package org.edupoll.Service;

import java.util.ArrayList;
import java.util.List;

import org.edupoll.model.entity.Moim;
import org.edupoll.repository.MoimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MoimService {

	@Autowired
	MoimRepository moimRepository;
	
	//모임 생성
	public String createMoim(Moim moim, String logonId) {
			moim.setCurrentPerson(1);
			moim.setManagerId(logonId);
			Moim saved = moimRepository.save(moim);
			
			return saved.getId();
	}
	
	//모임 찾기
	public Moim findMoim(String logonId){
		
		Moim found = moimRepository.findById(logonId).orElse(null);
		
		
		return found;
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
