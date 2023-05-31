package org.edupoll.repository;


import java.util.List;

import org.edupoll.model.entity.Reply;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	List<Reply> findByMoimIdOrderByIdAsc(String moimId, PageRequest page);
	
	List<Reply> findByMoimIdOrderByIdAsc(String moimId);
	
	  Long countByMoimId(String moimId);

}
