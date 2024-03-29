package org.edupoll.repository;

import org.edupoll.model.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Integer>{

	boolean existsByOwnerIdIsAndTargetIdIs(String ownerId, String userId);
	
	void deleteByOwnerIdIsAndTargetIdIs(String ownerId, String userId);
}
