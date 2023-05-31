package org.edupoll.controller.API;

import org.edupoll.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/api")
public class AttendanceAPIController {
	
	@Autowired
	AttendanceService attendanceService;
	
	
	@PostMapping("/attendance/join")
	@ResponseBody
	public String AttendMoimJoinHandle(@SessionAttribute String logonId, String moimId) {
		
		boolean rst = attendanceService.addNewMoimAttendance(logonId, moimId);
		
		return String.valueOf(rst);
	}
	
	@GetMapping("/attendance/delete") 
	
	public String deleteAttendHandle(int id, String moimId) {
		
		boolean rst = attendanceService.deleteAttend(id);
		
			if(rst) {
				return "/private/view"+moimId;
			} else {
				
				return "redirect:/private/list";
			}
	
	}
}
