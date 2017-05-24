package com.example.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.model.TaskUserBid;


@Repository
public interface TaskUserBidRepository extends PagingAndSortingRepository<TaskUserBid, Long> {
	
	
	TaskUserBid findByTaskIdAndUserId(Long task_id, Long user_id);
}
