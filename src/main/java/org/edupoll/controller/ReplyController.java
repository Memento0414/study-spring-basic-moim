package org.edupoll.controller;

import org.edupoll.Service.MoimService;
import org.edupoll.Service.ReplyService;
import org.edupoll.model.entity.Reply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReplyController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	ReplyService replyService;
	
	@Autowired
	 MoimService moimService;

	//댓글 등록
	@PostMapping("/moim/reply-task")
	public String AddReply(Reply reply, String moimId, Model model) {
		logger.debug("AddReply's result = {}", moimId);
		boolean rst = replyService.addNewReply(reply, moimId);
		
		if(rst) {

			return "redirect:/moim/view?id=" + moimId;
		} else {
			model.addAttribute("error", true);
			return "moim/view";
		}
		
	}
	
	@GetMapping("/moim/reply-delete")

	public String deleteReply(Reply reply, String password, Model model) {

		boolean rst = replyService.deleteReply(reply, password);
		logger.debug("deleteReply's result = {} ", rst);
		if (rst) {
			return "redirect:/moim/view";

		} else {
			model.addAttribute("error", true);
			return "moim/view";
		}
	}
  
}
