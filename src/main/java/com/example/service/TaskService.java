package com.example.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.example.dto.TaskDTO;
import com.example.dto.TaskLiteDTO;
import com.example.dto.TaskWithPeriodDTO;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
public interface TaskService {
    TaskDTO createTask(Map<String, Object> values) throws InvocationTargetException, IllegalAccessException;
    List<TaskDTO> listAllTask();
    
    List<TaskLiteDTO> getTaskWithoutUser(Long boardId);
    
    TaskDTO getTask(Long id);
    TaskDTO updateTask(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException;
    void deleteTask(Long id);

    TaskWithPeriodDTO addTaskTag(Long idTask, Long idTag) throws InvocationTargetException, IllegalAccessException;
}
