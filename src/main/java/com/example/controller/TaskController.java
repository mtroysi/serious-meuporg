package com.example.controller;

import com.example.dto.TaskDTO;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<TaskDTO> listTask(){
        return taskService.listTask();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public TaskDTO createTask(@RequestBody String title) {
        return taskService.createTask(title);
    }

}
