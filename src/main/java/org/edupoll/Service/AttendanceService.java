package org.edupoll.Service;

import java.util.Optional;

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

	
	@Transactional
	public boolean addNewMoimAttendance(String userId, String moimId) {
		
		Optional<User> user = userRepository.findById(userId);
		Optional<Moim> moim= moimRepository.findById(moimId);
		
		if(user.isEmpty() || moim.isEmpty()) {
			return false;
		}
		if(attendanceRepository.existsByUserIdIsAndMoimIdIs(userId, moimId)) {
			return false;
		}
		if(moim.get().getCurrentPerson() == moim.get().getMaxPerson()) {
			return false;
		}
		
		
		Attendance one = new Attendance(user.get(), moim.get());
		attendanceRepository.save(one);
		
		moim.get().setCurrentPerson(moim.get().getCurrentPerson()+1);
		moimRepository.save(moim.get());
		
		return true;
	}
	
	public boolean deleteAttend(Integer id) {
		
		if(attendanceRepository.findById(id).isPresent()) 
			attendanceRepository.deleteById(id);
			return true;
		
		
	}
	
	
}
