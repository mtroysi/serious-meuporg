package com.example.service;

import java.util.List;

import com.example.dto.TaskUserBidDTO;


public interface TaskUserBidService {

    List<TaskUserBidDTO> getTaskUserByBoardAndUser(Long idBoard, Long idUser);
}
