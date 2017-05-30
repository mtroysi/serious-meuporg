package com.example.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.example.dto.TaskWithPeriodDTO;
import com.example.enumeration.PriorityEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.example.dto.TaskUserDTO;
import com.example.service.TaskUserService;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
@RestController
@RequestMapping("/api/taskUser")
public class TaskUserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    TaskUserService taskUserService;
    
    /**
     * Retorune la liste des taches(taskUser) pour un tableau et un utilisateur
     * @param user_id : id utilisateur
     * @param board_id : id tableau
     * @return liste de tache
     */
    @RequestMapping(value = "/board/{board_id}/user/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<List<TaskUserDTO>> listTaskByBoardAndUser(@PathVariable(value = "user_id") Long user_id, @PathVariable(value = "board_id") Long board_id){
        logger.info("Calling TaskUserController.listTaskByBoardAndUser with {}  {}", user_id, board_id);
        
        List<TaskUserDTO> list = taskUserService.getTaskUserByUserIdAndBoardId(user_id, board_id);
        
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * Retourne la liste des taches par utilisateur
     * @param user_id : id de l'utilisateur
     * @return liste des taches
     */
    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<List<TaskUserDTO>> listTaskByUser(@PathVariable(value = "user_id") Long user_id){
        logger.info("Calling TaskUserController.listTaskByUser with {}", user_id);
        
        List<TaskUserDTO> list = taskUserService.getTaskUserByUserId(user_id);
        
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * Retourne la liste des taches par tableau
     * @param board_id : id du tableau
     * @return liste des taches
     */
    @RequestMapping(value = "/board/{board_id}", method = RequestMethod.GET)
    public ResponseEntity<List<TaskUserDTO>> listTaskByBoard(@PathVariable(value = "board_id") Long board_id){
    	logger.info("Calling TaskUserController.listTaskByBoardAndUser with {}", board_id);
        
        List<TaskUserDTO> list = taskUserService.getTaskUserByBoardId(board_id);
        
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Modifie une tâche.
     * @param id id de la tâche à modifier
     * @param values données modifiées de la tâche
     * @return tache après modification
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TaskUserDTO updateTask(@PathVariable("id") Long id, @RequestBody Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return taskUserService.updateTask(id, values);
    }
    
    /**
     * Modifie la TaskUser avec une nouvelle colonne kanban
     * @param idTaskUser
     * @param idColumn
     * @return TaskUserDTO
     */
    @RequestMapping(value = "/{idTaskUser}/column", method = RequestMethod.PUT)
    public TaskUserDTO updateColumnTask(@PathVariable("idTaskUser") Long idTaskUser, @RequestBody(required=false) Long idColumn){
        return taskUserService.updateColumnTask(idTaskUser, idColumn);
    }
    
    /**
     * Modifie la TaskUser avec une nouvelle priority
     * @param idTaskUser
     * @param priority
     * @return TaskUserDTO
     */ 
    @RequestMapping(value = "/{idTaskUser}/priority", method = RequestMethod.PUT)
    public TaskUserDTO updatePriorityTask(@PathVariable("idTaskUser") Long idTaskUser, @RequestBody(required=false) String priority){
        return taskUserService.updatePriorityTask(idTaskUser, PriorityEnum.valueOf(priority));
    }

    /**
     * Créé une tâche.
     * @param values données de la tâche
     * @return tache après création
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public TaskUserDTO createTask(@RequestBody Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return taskUserService.createTask(values);
    }

    /**
     * Supprime une tâche
     * @param id id de la tâche à supprimer
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable("id") Long id) {
        taskUserService.deleteTask(id);
    }
}
