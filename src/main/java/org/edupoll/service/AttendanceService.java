package org.edupoll.service;

import java.util.List;
import java.util.Optional;

import org.edupoll.model.dto.response.AttendanceJoinResponseData;
import org.edupoll.model.entity.Attendance;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.User;
import org.edupoll.repository.AttendanceRepository;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MoimRepository moimRepository;

	
	/**모임 참가 신청을 하는 메서드*/
	@Transactional
	public AttendanceJoinResponseData addNewMoimAttendance(String userId, String moimId) {
		
		AttendanceJoinResponseData ajrd = new AttendanceJoinResponseData();
		
		Optional<User> user = userRepository.findById(userId);
		Optional<Moim> moim= moimRepository.findById(moimId);
		
		if(user.isEmpty() || moim.isEmpty()) {
			ajrd.setResult(false);
			ajrd.setErrorMessage("유효하지 않은 정보가 전송 되었습니다.");
			return ajrd;
		}
		if(attendanceRepository.existsByUserIdIsAndMoimIdIs(userId, moimId)) {
			ajrd.setResult(false);
			ajrd.setErrorMessage("이미 참가중인 모임입니다.");
			return ajrd;
		}
		if(moim.get().getCurrentPerson() == moim.get().getMaxPerson()) {
			ajrd.setResult(false);
			ajrd.setErrorMessage("최대 참가 인원 수를 초과하였습니다.");
			return ajrd;
		}
		if(moim.get().getManager().getId().equals(user.get().getId())) {
			
			ajrd.setResult(false);
			ajrd.setErrorMessage("모임주최자는 모임에 참가 할 수 없습니다.");
			return ajrd;
			
		}
		
		Attendance one = new Attendance(user.get(), moim.get());
		attendanceRepository.save(one);
		
		moim.get().setCurrentPerson(moim.get().getCurrentPerson()+1);
		moimRepository.save(moim.get());
		ajrd.setResult(true);
		ajrd.setCurrentPerson(moim.get().getCurrentPerson());
		
		
		List<String> alist = attendanceRepository.findByMoimIdIs(moimId).stream().map(t->t.getUser().getId()).toList();
		
		ajrd.setAttendUserIds(alist);
		
		return ajrd;
	}

	
	@Transactional
	public AttendanceJoinResponseData cancelAttendance(String userId, String moimId) {
		
		AttendanceJoinResponseData ajrd = new AttendanceJoinResponseData();
		
		attendanceRepository.deleteByUserIdIsAndMoimIdIs(userId, moimId);
		ajrd.setResult(true);
		Moim moim = moimRepository.findById(moimId).get();
		moim.setCurrentPerson(moim.getCurrentPerson()-1);
		Moim saved = moimRepository.save(moim);
		
		ajrd.setCurrentPerson(saved.getCurrentPerson());
		List<String> ids = attendanceRepository.findByMoimIdIs(moimId).stream().map(t->t.getUser().getId()).toList();
		ajrd.setAttendUserIds(ids);
		
		return ajrd;
		
		
	}
	
	
	/**특정 유저가 특정모임에 참여중인 확인하고자 할때 사용할 메서드*/

	public boolean CheckJoinedAttend(String userId, String moimId) {
		
		return attendanceRepository.existsByUserIdIsAndMoimIdIs(userId, moimId);
		
	}
}
