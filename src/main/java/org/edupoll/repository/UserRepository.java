package org.edupoll.repository;

import java.util.List;

import org.edupoll.model.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, String>{


	long findByIdContainingOrNickContainingAllIgnoreCase(String id, String nick);
	
	List<User> findByIdContainingOrNickContainingAllIgnoreCase(String id, String nick, PageRequest pageReq);

	

}
