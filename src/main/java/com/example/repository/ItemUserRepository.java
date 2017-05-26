package com.example.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ItemUser;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */

@Repository
public interface ItemUserRepository extends PagingAndSortingRepository<ItemUser, Long> {
	
	List<ItemUser> findByUserId(Long userId);
}
