package com.example.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CommentDTO;
import com.example.dto.TaskDTO;
import com.example.dto.TaskWithPeriodDTO;
import com.example.service.CommentService;
import com.example.service.TaskService;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;
    @Autowired
    CommentService commentService;

    /**
     * Ajoute un tag à une tâche.
     * @param idTask id de la tâche
     * @param idTag id du tag
     * @return tâche mise à jour
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/{idTask}/tag/{idTag}", method = RequestMethod.PUT)
    public TaskWithPeriodDTO addTaskTag(@PathVariable("idTask") Long idTask, @PathVariable("idTag") Long idTag) throws InvocationTargetException, IllegalAccessException {
        return taskService.addTaskTag(idTask, idTag);
    }

    /**
     * Retourne la tâche dont l'id est passé en paramètre
     * @param id id de la tâche
     * @return la tâche correspondante
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TaskDTO getTask(@PathVariable("id") Long id) {
        return taskService.getTask(id);
    }

    /**
     * Retourne la liste de toutes les taches
     * @return liste des taches
     */
    public List<TaskDTO> listAllTask() {
        return taskService.listAllTask();
    }

    /**
     * Crée une tâche.
     * @param values données de la tĉhe à créer
     * @return TaskDTO
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(method = RequestMethod.POST)
    public TaskDTO createTask(@RequestBody Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return taskService.createTask(values);
    }

    /**
     * Supprime une tâche
     * @param id id de la tâche à supprimer
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }

    /**
     * Ajoute un commentaire à une tâche
     * @param id id de la tâche
     * @param comment commentaire à ajouter
     * @return CommentDTO
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public CommentDTO addCommentToTask(@PathVariable("id") Long id, @RequestBody Map<String, Object> comment) throws InvocationTargetException, IllegalAccessException {
        Long idUser = Long.parseLong((String) comment.get("creator"));
        System.out.println(idUser);
        comment.remove("creator");
        return commentService.addCommentToTask(id, comment, idUser);
    }
}
