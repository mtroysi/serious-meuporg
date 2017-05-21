package com.example.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.model.TaskUser;


@Repository
public interface TaskUserRepository extends PagingAndSortingRepository<TaskUser, Long> {
	
	List<TaskUser> findAllByUserIdAndTaskBoardId(Long userId, Long boardId);
	
	List<TaskUser> findAllByTaskBoardId(Long boardId);
	
	List<TaskUser> findAllByUserId(Long boardId);
}
