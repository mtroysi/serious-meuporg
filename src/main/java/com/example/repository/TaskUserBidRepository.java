package com.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.model.TaskUserBid;


@Repository
public interface TaskUserBidRepository extends PagingAndSortingRepository<TaskUserBid, Long> {
	
}
