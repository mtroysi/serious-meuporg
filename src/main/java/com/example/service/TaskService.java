package com.example.service;

import com.example.dto.TaskDTO;
import com.example.model.Task;

import java.util.List;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
public interface TaskService {
    public TaskDTO createTask(String name);
    public List<TaskDTO> listTask();
}
