package com.example.service.impl;

import com.example.dto.TaskDTO;
import com.example.model.Task;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;
import com.example.transformers.Transformers;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private Transformers transformers;

    @Override
    public TaskDTO createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setDateCreation(Calendar.getInstance().getTime());
        return new TaskDTO(taskRepository.save(task));
    }

    @Override
    public List<TaskDTO> listTask() {
        Iterable<Task> res = taskRepository.findAll();
        List<TaskDTO> list = new ArrayList<>();
        for (Task task : res) {
            list.add(new TaskDTO(task));
        }
        return list;
    }

    @Override
    public TaskDTO updateTask(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        //TODO: vérifier si l'utilisateur connecté a le droit de modification sur le tableau
        Task task = taskRepository.findOne(id);
        BeanUtils.populate(task, values);
        return (TaskDTO) transformers.convertEntityToDto(taskRepository.save(task), TaskDTO.class);
    }
}
