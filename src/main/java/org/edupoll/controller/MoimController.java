package org.edupoll.controller;

import java.util.List;

import org.edupoll.Service.MoimService;
import org.edupoll.Service.ReplyService;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.Reply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;

@Controller
public class MoimController {
	Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired
	MoimService moimService;
	
	@Autowired
	ReplyService replyService;
	
	
	//모임 생성페이지 뷰로 이동
	@GetMapping("/moim/write")
	public String goToMoim() {
		
		return "moim/write";
	}
	
	// 모임 생성 제어
	@PostMapping("/moim/write")
	public String addNewMoimHandle(Moim moim, @SessionAttribute String logonId) {
		String writeId = moimService.createMoim(moim, logonId);
		logger.debug("searchHandle's result : {} ", writeId);
		
		return "/moim/list";
	}
	
	//모임 목록 페이징 불러오기 및 페이징처리
	
	@GetMapping("/moim/list")
	public String findMoimsHandle(@RequestParam(defaultValue = "1") int page, Model model) {
		
		List<Moim> list = moimService.findAllMoim(page);
		model.addAttribute("list", list);
		
		List<String> pages = moimService.MoimPaggingCount(page);
		model.addAttribute("pages" ,pages);
		
		return "moim/list";
		
	}
	
	@GetMapping("/moim/view")
	public String showMoimDetail(String id, @RequestParam(defaultValue = "1")int page, Model model) {
		
		logger.debug("showMoimDetail's reusult = {}", id);
		
		model.addAttribute("moim", moimService.findMoim(id));
		
		List<Reply> replys = replyService.findAll(id, page);
		model.addAttribute("replys" ,replys);
		
		List<String> pages = replyService.replyPagging(page);
		
		model.addAttribute("replyPage", pages);
		
		return "moim/view";
	}
}
