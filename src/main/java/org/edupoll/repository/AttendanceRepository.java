package org.edupoll.repository;

import java.util.List;

import org.edupoll.model.entity.Attendance;
import org.edupoll.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{
	
	boolean existsByUserIdIsAndMoimIdIs(String userId, String moimId);
	
	List<Attendance> findByMoimIdIs(String moimId);
	
	Attendance findByUserIdIsAndMoimIdIs(String userId, String moimId);

	void deleteByUserIdIsAndMoimIdIs(String userId, String moimId);


	
}
