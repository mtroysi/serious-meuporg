package com.example.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.TaskDTO;
import com.example.dto.TaskLiteDTO;
import com.example.service.TaskService;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TaskDTO listTask(@PathVariable("id") Long id) {
        return taskService.listTask(id);
    }

    public List<TaskDTO> listAllTask() {
        return taskService.listAllTask();
    }

    @RequestMapping(method = RequestMethod.POST)
    public TaskDTO createTask(@RequestBody Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return taskService.createTask(values);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TaskDTO updateTask(@PathVariable("id") Long id, @RequestBody Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return taskService.updateTask(id, values);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }    
}
