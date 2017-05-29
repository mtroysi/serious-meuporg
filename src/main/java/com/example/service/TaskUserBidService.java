package com.example.service;

import java.util.List;

import com.example.dto.BidDTO;
import com.example.dto.TaskUserBidDTO;


public interface TaskUserBidService {
	
	/**
	 * Retourne la liste des taches aux enchères par rapport à un tableau et un utilisateur
	 * @param idBoard
	 * @param idUser
	 * @return
	 */
    List<TaskUserBidDTO> getTaskUserBidByBoardAndUser(Long idBoard, Long idUser);
    
    
    /**
     * Ajoute le temps à une enchère par rapport à une tache
     * @param idTask
     * @param duration
     * @return
     */
    TaskUserBidDTO addOrUpdateTaskUserBid(Long idTask, Double duration);
    
    
    /**
     * Ajoute une liste de taches aux enchères
     * @param listTaskId
     * @param dateEnd
     * @return
     */
    List<TaskUserBidDTO> addNewTaskInBid(List<Long> listTaskId, Long dateEnd);
    
    
    /**
     * Retourne une liste de taches par rapport à la date de fin des enchères
     * @param idBoard
     * @return
     */
    List<TaskUserBidDTO> getTaskUserBidEndByBoard(Long idBoard);
    
    
    /**
     * Gestion de la validation d'une enchère
     * @param idBoard
     * @param listBidDTO
     */
    void validBidByBoard(Long idBoard, List<BidDTO> listBidDTO);
}
