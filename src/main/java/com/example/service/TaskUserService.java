package com.example.service;

import java.util.List;

import com.example.dto.TaskUserDTO;
import com.example.model.TaskUser;


public interface TaskUserService {
	
	/**
	 * Get list of taskUser by user id 
	 * @param userId
	 * @return
	 */
	List<TaskUserDTO> getTaskUserByUserIdAndBoardId(Long userId, Long boardId);
	
	/**
	 * Get list of taskUser by board id
	 * @param boardId
	 * @return
	 */
	List<TaskUserDTO> getTaskUserByBoardId(Long boardId);	
}
