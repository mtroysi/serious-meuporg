package com.example.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.example.dto.BoardDTO;
import com.example.dto.TaskDTO;
import com.example.dto.TaskLiteDTO;
import com.example.dto.TaskWithPeriodDTO;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
public interface TaskService {
	
	/**
     * Crée une tâche
     *
     * @param values données de la tâche à créer
     * @return DTO de la tâche créée
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    TaskDTO createTask(Map<String, Object> values) throws InvocationTargetException, IllegalAccessException;
    
    /**
     * Retourne toutes les tâches en base de données
     *
     * @return liste de toutes les tâches en base de données
     */
    List<TaskDTO> listAllTask();
    
    
    /**
     * Retourne toutes les tâches non-assignées d'un tableau
     *
     * @param boardId id du tableau
     * @return tâches non-assignées du tableau
     */
    List<TaskLiteDTO> getTaskWithoutUser(Long boardId);
    
    
    /**
     * Retourne la tâche dont l'id est passé en paramètre
     *
     * @param id l'id de la tâche
     * @return la tâche correspondante
     */
    TaskDTO getTask(Long id);
    
    
    /**
     * Supprime une tâche
     *
     * @param id l'id de la tâche à supprimer
     */
    void deleteTask(Long id);
    
    
    /**
     * Ajoute ou supprime un tag à une tâche
     *
     * @param idTask id de la tâche à modifier
     * @param idTag  id du tag à ajouter ou enlever
     * @return la tâche modifiée
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    TaskWithPeriodDTO addTaskTag(Long idTask, Long idTag) throws InvocationTargetException, IllegalAccessException;

    /**
     * Retourne le tableau à partir de l'id d'une tâche
     * @param id de la tâche
     * @return id du tableau
     */
    BoardDTO getBoardFromTask(Long id);
}
