package org.edupoll.repository;


import java.util.List;

import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.Reply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	List<Reply> findByMoimIdOrderByIdAsc(String moimId, Pageable pageable);
	
	List<Reply> findByMoimOrderByIdAsc(Moim moimId, Pageable pageable);
}
