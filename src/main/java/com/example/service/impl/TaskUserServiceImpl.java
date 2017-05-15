package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.TaskUserDTO;
import com.example.model.TaskUser;
import com.example.repository.TaskUserRepository;
import com.example.service.TaskUserService;
import com.example.transformers.Transformers;


@Service
public class TaskUserServiceImpl implements TaskUserService {
    
	@Autowired
    private TaskUserRepository taskUserRepository;
	
    @Autowired
    Transformers transformers;
    
    
	@Override
	public List<TaskUserDTO> getTaskUserByUserIdAndBoardId(Long userId, Long boardId) {
		return taskUserRepository.findAllByUserIdAndTaskBoardId(userId, boardId).stream().map(
				(TaskUser tu) -> (TaskUserDTO)transformers.convertEntityToDto(tu, TaskUserDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<TaskUserDTO> getTaskUserByBoardId(Long boardId) {
		return taskUserRepository.findByTaskBoardId(boardId).stream().map(
				(TaskUser tu) -> (TaskUserDTO)transformers.convertEntityToDto(tu, TaskUserDTO.class)).collect(Collectors.toList());
	}
   
}
