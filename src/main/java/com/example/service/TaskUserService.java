package com.example.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.example.dto.TaskUserDTO;
import com.example.enumeration.PriorityEnum;


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

	/**
	 * Modifie une tâche
	 *
	 * @param id     l'id de la tâche à modifier
	 * @param values données modifiées
	 * @return DTO de la tâche modifiée
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	TaskUserDTO updateTask(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException;
	
	/**
	 * Modifie la colonne kanban d'une tache
	 * @param idtaskUser 
	 * @param idColumn
	 * @return DTO de la tâche modifiée
	 */
	TaskUserDTO updateColumnTask(Long idtaskUser, Long idColumn);
	
	/**
	 * Modifie le priorité d'une tache
	 * @param idtaskUser 
	 * @param status
	 * @return DTO de la tâche modifiée
	 */
	TaskUserDTO updatePriorityTask(Long idtaskUser, PriorityEnum priority);
}
