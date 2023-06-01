package org.edupoll.controller.API;

import org.edupoll.Service.AttendanceService;
import org.edupoll.model.dto.response.AttendanceJoinResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api")
public class AttendanceAPIController {
	
	@Autowired
	AttendanceService attendanceService;
	
	
	@PostMapping("/attendance/join")
	public AttendanceJoinResponseData AttendMoimJoinHandle(@SessionAttribute String logonId, String moimId) {
		
		AttendanceJoinResponseData rst = attendanceService.addNewMoimAttendance(logonId, moimId);
		
		return rst;
	}
	
	
	@DeleteMapping("/attendance/cancel")
	public AttendanceJoinResponseData attendanceCancelHandle(@SessionAttribute String logonId, String moimId) {
		
		return attendanceService.cancelAttendance(logonId, moimId);
	}
	
	
}
