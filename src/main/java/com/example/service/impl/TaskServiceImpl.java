package com.example.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.TaskDTO;
import com.example.dto.TaskLiteDTO;
import com.example.dto.TaskWithPeriodDTO;
import com.example.enumeration.PriorityEnum;
import com.example.enumeration.StatusEnum;
import com.example.model.ColonneKanban;
import com.example.model.Tag;
import com.example.model.Task;
import com.example.model.TaskUser;
import com.example.repository.ColonneKanbanRepository;
import com.example.repository.TagRepository;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;
import com.example.transformers.Transformers;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ColonneKanbanRepository colonneKanbanRepository;
    @Autowired
    private Transformers transformers;
    
    
    /* 
     * (non-Javadoc)
     * @see com.example.service.TaskService#createTask(java.util.Map)
     */
    @Override
    public TaskDTO createTask(Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        Task task = new Task();
        BeanUtils.setProperty(task, "priority", PriorityEnum.valueOf((String) values.get("priority")));
        values.remove("priority");
        BeanUtils.populate(task, values);
        return (TaskDTO) transformers.convertEntityToDto(taskRepository.save(task), TaskDTO.class);
    }

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.TaskService#listAllTask()
     */
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

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.TaskService#getTask(java.lang.Long)
     */
    @Override
    public TaskDTO getTask(Long id) {
        return (TaskDTO) transformers.convertEntityToDto(taskRepository.findOne(id), TaskDTO.class);
    }

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.TaskService#updateTask(java.lang.Long, java.util.Map)
     */
    @Override
    public TaskWithPeriodDTO updateTask(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        Task task = taskRepository.findOne(id);

        task.setPriority(PriorityEnum.valueOf((String) values.get("priority")));
        values.remove("priority");

        values.remove("taskComments");
        values.remove("tags");

        logger.info(String.valueOf(values));
        BeanUtilsBean.getInstance().getConvertUtils().register(false, true, 0);
        BeanUtils.populate(task, values);

        List<TaskUser> taskUsers = task.getTaskUsers();
        taskUsers.stream().forEach(taskUser -> {
            Map<String, Object> colonneValues = (Map<String, Object>) values.get("colonneKanban");
            ColonneKanban colonneKanban = colonneKanbanRepository.findOne(new Long((Integer) colonneValues.get("id")));
            taskUser.setColonneKanban(colonneKanban);
        });
        task.setTaskUsers(taskUsers);

        return (TaskWithPeriodDTO) transformers.convertEntityToDto(taskRepository.save(task), TaskWithPeriodDTO.class);
    }

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.TaskService#deleteTask(java.lang.Long)
     */
    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

   
    /* 
     * (non-Javadoc)
     * @see com.example.service.TaskService#addTaskTag(java.lang.Long, java.lang.Long)
     */
    @Override
    public TaskWithPeriodDTO addTaskTag(Long idTask, Long idTag) throws InvocationTargetException, IllegalAccessException {
        Task task = taskRepository.findOne(idTask);
        Tag tag = tagRepository.findOne(idTag);

        List<Tag> tagList = task.getTags();
        if (tagList.stream().anyMatch(t -> t.getId().equals(tag.getId()))) {
            tagList = tagList.stream().filter(t -> !t.getId().equals(tag.getId())).collect(Collectors.toList());
        } else {
            tagList.add(tag);
        }
        task.setTags(tagList);

        return (TaskWithPeriodDTO) transformers.convertEntityToDto(taskRepository.save(task), TaskWithPeriodDTO.class);
    }


    /* 
     * (non-Javadoc)
     * @see com.example.service.TaskService#getTaskWithoutUser(java.lang.Long)
     */
    @Override
    public List<TaskLiteDTO> getTaskWithoutUser(Long boardId) {
        List<Task> list = taskRepository.findTaskByUserIsNullAndBoardId(boardId);

        return list.stream().map((Task task) -> (TaskLiteDTO) transformers.convertEntityToDto(task, TaskLiteDTO.class)).collect(Collectors.toList());
    }
}
