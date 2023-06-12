package org.edupoll.controller;

import java.util.List;

import org.edupoll.Service.AttendanceService;
import org.edupoll.Service.MoimService;
import org.edupoll.Service.ReplyService;
import org.edupoll.model.dto.response.Pagination;
import org.edupoll.model.entity.Moim;

import org.edupoll.security.support.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MoimController {
	Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired
	MoimService moimService;
	
	@Autowired
	ReplyService replyService;
	
	@Autowired
	AttendanceService attendanceService;
	
	
	//모임 생성페이지 뷰로 이동
	@GetMapping("/moim/write")
	public String goToMoim() {
		
		return "moim/write";
	}
	
	// 모임 생성 제어
	@PostMapping("/moim/write")
	public String addNewMoimHandle(Moim moim, @AuthenticationPrincipal Account account) {
		String writeId = moimService.createMoim(moim, account.getUsername());
		logger.debug("searchHandle's result : {} ", writeId);
		
		return "redirect:/moim/list";
	}
	
	//모임 목록 페이징 불러오기 및 페이징처리
	
	@GetMapping("/moim/list")
	public String findMoimsHandle(@RequestParam(defaultValue = "1") int page, Model model) {
		
		List<Moim> list = moimService.findAllMoim(page);
		model.addAttribute("list", list);
		
		Pagination pagination = moimService.MoimPaggingCount(page);
		model.addAttribute("pagination" ,pagination);
		
		return "moim/list";
		
	}
	//특정 모임 정보 보기용 EndPoint + 리플 정보도 같이
	@GetMapping("/moim/view")
	public String showMoimDetail(String id,@RequestParam(defaultValue = "1")int page, @AuthenticationPrincipal Account account, Model model) {
		
		logger.debug("showMoimDetail's reusult = {}", id);
		
		
		model.addAttribute("moim", moimService.findMoim(id));
		
		model.addAttribute("isLogon" , account.getUsername() != null);
		
		if(account.getUsername() != null) {
		model.addAttribute("isJoined", attendanceService.CheckJoinedAttend(account.getUsername(), id));

		}

		
		//페이징 처리
		List<String> pages = replyService.replyPagging(page, id);
		model.addAttribute("replyPage", pages);
		
		return "moim/view";
	}
	
	@GetMapping("/moim/delete")
	public String deleteMoim(String id) {
		
		boolean rst = moimService.deleteMoim(id);
		
		return "/moim/list";
		
		
	}
}
