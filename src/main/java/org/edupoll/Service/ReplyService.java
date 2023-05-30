package org.edupoll.Service;

import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.Reply;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	
}
