package org.edupoll.service;

import java.util.ArrayList;
import java.util.List;

import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.Reply;
import org.edupoll.model.entity.User;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
	
	@Autowired
	ReplyRepository replyRepository;

	@Autowired
	MoimRepository moimRepository;
	
	/**모임 생성 메서드*/
	public boolean addNewReply(Reply reply, String moimId) {

		Moim moim = moimRepository.findById(moimId).get();
		reply.setMoim(moim);
		System.out.println("reply = " + reply);

		replyRepository.save(reply);

		return true;
	}
	
	/**특정 모임 찾는 메서드*/
	public List<Reply> findByReply(String moimId) {

		return replyRepository.findByMoimIdOrderByIdAsc(moimId);

	}

	/**페이징 하기 메서드*/
	public List<Reply> pageByReply(String moimId, int page) {
		PageRequest pages = PageRequest.of(page - 1, 10);

		return replyRepository.findByMoimIdOrderByIdAsc(moimId, pages);
	}

	/**페이징 처리를 위한 페이지수*/
	public List<String> replyPagging(int page, String moimId) {

		
		long totalData = replyRepository.countByMoimId(moimId);
			System.out.println("totalData => " +totalData);
		List<String> pages = new ArrayList<>();

		for (int i = 1; i <= totalData / 10 + (totalData % 10 > 0 ? 1 : 0); i++) {
			pages.add(String.valueOf(i));
		}
		return pages;
	}

	public boolean deleteReply(Reply reply) {
	
		
		return false;
	}
	
	
	
}
