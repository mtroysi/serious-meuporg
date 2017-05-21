package com.example.service;

import java.util.Date;
import java.util.List;

import com.example.dto.TaskUserBidDTO;


public interface TaskUserBidService {

    List<TaskUserBidDTO> getTaskUserBidByBoardAndUser(Long idBoard, Long idUser);
}
