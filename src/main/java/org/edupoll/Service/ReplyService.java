package org.edupoll.Service;

import java.util.ArrayList;
import java.util.List;

import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.Reply;
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

	public boolean addNewReply(Reply reply, String moimId) {

		Moim moim = moimRepository.findById(moimId).get();
		reply.setMoim(moim);
		System.out.println("reply = " + reply);

		replyRepository.save(reply);

		return true;
	}

	public List<Reply> findAll(String moimId) {

		return replyRepository.findByMoimIdOrderByIdAsc(moimId);

	}

	public List<Reply> findAll(String moimId, int page) {
		PageRequest pages = PageRequest.of(page - 1, 10);

		return replyRepository.findByMoimIdOrderByIdAsc(moimId, pages);
	}

	public List<String> replyPagging(int page) {

		long totalPage = replyRepository.count();

		List<String> pages = new ArrayList<>();

		for (int i = 1; i <= totalPage / 10 + (totalPage % 10 > 0 ? 1 : 0); i++) {
			pages.add(String.valueOf(i));
		}
		return pages;
	}
	
	public boolean deleteReply(String moimId) {
		
		
		return false;
		
	}
	
}
