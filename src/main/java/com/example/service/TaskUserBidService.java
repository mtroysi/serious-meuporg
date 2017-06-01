package com.example.service;

import java.util.List;

import com.example.dto.BidDTO;
import com.example.dto.TaskUserBidDTO;


public interface TaskUserBidService {
	
	/**
	 * Retourne la liste des taches aux enchères par rapport à un tableau et un utilisateur
	 * @param idBoard
	 * @param idUser
	 * @return la liste des taches (TaskUserBidDTO)
	 */
    List<TaskUserBidDTO> getTaskUserBidByBoardAndUser(Long idBoard, Long idUser);
    
    
    /**
     * Ajoute le temps à une enchère par rapport à une tache
     * @param idTaskUser
     * @param duration
     * @return tache (TaskUserBidDTO)
     */
    TaskUserBidDTO addOrUpdateTaskUserBid(Long idTaskUser, Double duration);
    
    
    /**
     * Ajoute une liste de tachesUser aux enchères
     * @param listIdTaskUsers
     * @param dateEnd
     * @return la liste des taches créées (TaskUserBidDTO)
     */
    List<TaskUserBidDTO> addNewTaskInBid(List<Long> listIdTaskUsers, Long dateEnd);
    
    
    /**
     * Retourne une liste de taches par rapport à la date de fin des enchères
     * @param idBoard
     * @return la liste des taches 
     */ 
    List<TaskUserBidDTO> getTaskUserBidEndByBoard(Long idBoard);
    
    
    /**
     * Gestion de la validation d'une enchère
     * @param idBoard
     * @param listBidDTO
     */
    void validBidByBoard(Long idBoard, List<BidDTO> listBidDTO);
}
