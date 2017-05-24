package com.example.service;

import java.util.List;

import com.example.dto.TaskUserBidDTO;


public interface TaskUserBidService {

    List<TaskUserBidDTO> getTaskUserBidByBoardAndUser(Long idBoard, Long idUser);
    
    TaskUserBidDTO addOrUpdateTaskUserBid(Long idTask, Double duration);
    
    List<TaskUserBidDTO> addNewTaskInBid(List<Long> listTaskId, Long dateEnd);
    
    List<TaskUserBidDTO> getTaskUserBidEndByBoard(Long idBoard);
}
