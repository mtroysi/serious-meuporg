package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.TaskUserBidDTO;
import com.example.service.TaskUserBidService;
import com.example.service.UserService;
import com.example.transformers.Transformers;


@Service
public class TaskUserBidServiceImpl implements TaskUserBidService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private Transformers transformers;

	@Override
	public List<TaskUserBidDTO>  getTaskUserByBoardAndUser(Long idBoard, Long idUser) {
		// TODO Auto-generated method stub
		return null;
	}

  
}
