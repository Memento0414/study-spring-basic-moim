package org.edupoll.repository;

import org.edupoll.model.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{
	
	boolean existsByUserIdIsAndMoimIdIs(String userId, String moimId);
	
}
