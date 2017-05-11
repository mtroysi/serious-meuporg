package com.example.service.impl;

import com.example.dto.TaskDTO;
import com.example.model.Task;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

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
}
