package com.example.service;

import com.example.dto.TaskDTO;
import com.example.model.Task;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
public interface TaskService {
    TaskDTO createTask(Map<String, Object> values) throws InvocationTargetException, IllegalAccessException;
    List<TaskDTO> listAllTask();
    TaskDTO listTask(Long id);
    TaskDTO updateTask(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException;
    void deleteTask(Long id);
}
