package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.enumeration.ItemEnum;
import com.example.model.ItemUser;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */

@Repository
public interface ItemUserRepository extends PagingAndSortingRepository<ItemUser, Long> {
	
	List<ItemUser> findByUserId(Long userId);
	
	@Modifying 
	@Transactional
	@Query(value = "DELETE FROM item_user WHERE item_id=?1", nativeQuery = true)
	void deleteByIdItem(Long itemId);
	
	@Modifying 
	@Transactional
	@Query(value = "UPDATE item_user SET active=?2 WHERE item_id=?1", nativeQuery = true)
	void updateActiveByIdItem(Long itemId, Boolean active);

	@Modifying 
	@Transactional
	@Query(value = "UPDATE item_user iu SET iu.active=false WHERE iu.item_id IN (SELECT id FROM item i WHERE i.type=?1)", nativeQuery = true)
	void updateNoActiveByType(String type);
}
