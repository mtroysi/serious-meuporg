package com.example.service;

import java.util.List;

import com.example.dto.TaskUserDTO;


public interface TaskUserService {
	
	/**
	 * Retourne les tâches d'un utilisateur donné pour un tableau donné
	 * @param userId id de l'utilisateur
	 * @param boardId id du tableau
	 * @return liste des tâches
	 */
	List<TaskUserDTO> getTaskUserByUserIdAndBoardId(Long userId, Long boardId);
	
	/**
	 * Retourne toutes les tâches d'un utilisateur donné
	 * @param userId id de l'utilisateur
	 * @return liste des tâches d'un utilisateur donné
	 */
	List<TaskUserDTO> getTaskUserByUserId(Long userId);
	
	/**
	 * Retourne toutes les tâches d'un tableau donné
	 * @param boardId id du tableau
	 * @return liste des tâches d'un tableau donné
	 */
	List<TaskUserDTO> getTaskUserByBoardId(Long boardId);	
}
