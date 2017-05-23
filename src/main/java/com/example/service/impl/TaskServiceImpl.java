package com.example.service.impl;

import com.example.dto.TaskDTO;
import com.example.enumeration.PriorityEnum;
import com.example.model.Tag;
import com.example.model.Task;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;
import com.example.transformers.Transformers;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
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
    public TaskDTO createTask(Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        Task task = new Task();
        BeanUtils.setProperty(task,"priority", PriorityEnum.valueOf((String) values.get("priority")));
        values.remove("priority");
        BeanUtils.populate(task, values);
        return (TaskDTO) transformers.convertEntityToDto(taskRepository.save(task), TaskDTO.class);
    }

    @Override
    public List<TaskDTO> listAllTask() {
        Iterable<Task> res = taskRepository.findAll();
        List<TaskDTO> list = new ArrayList<>();
        for (Task task : res) {
            TaskDTO dto = (TaskDTO) transformers.convertEntityToDto(task, TaskDTO.class);
            list.add(dto);
        }
        return list;
    }

    @Override
    public TaskDTO listTask(Long id) {
        return (TaskDTO) transformers.convertEntityToDto(taskRepository.findOne(id), TaskDTO.class);
    }

    @Override
    public TaskDTO updateTask(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        Task task = taskRepository.findOne(id);

        task.setPriority(PriorityEnum.valueOf((String) values.get("priority")));
        values.remove("priority");
        BeanUtils.populate(task, values);
        return (TaskDTO) transformers.convertEntityToDto(taskRepository.save(task), TaskDTO.class);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public void addTagToTask(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        Tag tag = new Tag();
        BeanUtils.populate(tag,values);
        Task task = taskRepository.findOne(id);
        List<Tag> tagList = task.getTags();
        tagList.add(tag);
        task.setTags(tagList);
        taskRepository.save(task);
    }
    
    @Override
    public List<TaskLiteDTO> getTaskWithoutUser(Long boardId){
    	List<Task> list = taskRepository.findTaskByUserIsNullAndBoardId(boardId);
    	
        return list.stream().map((Task task) -> (TaskLiteDTO)transformers.convertEntityToDto(task, TaskLiteDTO.class)).collect(Collectors.toList());
    }
}
