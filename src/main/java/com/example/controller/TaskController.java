package com.example.controller;

import com.example.dto.TaskDTO;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

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
