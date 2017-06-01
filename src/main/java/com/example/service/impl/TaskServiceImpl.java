package com.example.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.BoardDTO;
import com.example.dto.TaskDTO;
import com.example.dto.TaskLiteDTO;
import com.example.dto.TaskUserDTO;
import com.example.dto.TaskWithPeriodDTO;
import com.example.enumeration.PriorityEnum;
import com.example.model.BoardUser;
import com.example.model.Tag;
import com.example.model.Task;
import com.example.model.TaskUser;
import com.example.model.User;
import com.example.repository.TagRepository;
import com.example.repository.TaskRepository;
import com.example.repository.TaskUserRepository;
import com.example.repository.UserRepository;
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
    private TaskUserRepository taskUserRepository;
    
    @Autowired
    private TagRepository tagRepository;
    
    @Autowired
    private Transformers transformers;
    
    @Autowired
    private UserRepository userRepository;

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
     * @see com.example.service.TaskService#getTask(java.lang.Long)
     */
    @Override
    public TaskDTO getTask(Long id) {
        return (TaskDTO) transformers.convertEntityToDto(taskRepository.findOne(id), TaskDTO.class);
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
    public List<TaskUserDTO> getTaskWithoutUser(Long boardId) {
        List<TaskUser> list = taskUserRepository.findTaskUserByUserIsNullAndBoardId(boardId);
        return list.stream().map((TaskUser task) -> (TaskUserDTO) transformers.convertEntityToDto(task, TaskUserDTO.class)).collect(Collectors.toList());
    }

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.TaskService#getBoardFromTask(java.lang.Long)
     */
    @Override
    public BoardDTO getBoardFromTask(Long id) {
        Task task = taskRepository.findOne(id);
        return (BoardDTO)transformers.convertEntityToDto(task.getBoard(), BoardDTO.class);
    }
    
    
    /* 
     * (non-Javadoc)
     * @see com.example.service.TaskService#getTaskBidUser(java.lang.Long)
     */
    @Override
    public List<TaskDTO> getTaskBidUser(Long id){
    	 User user = userRepository.findOne(id);
    	 List<BoardUser> listUserBoard = user.getBoardUsers();
    	 List<TaskDTO> listDto = listUserBoard.stream()
    			 .flatMap((BoardUser u)-> {
    				return u.getBoard().getTasks().stream()
    				 .filter((Task t)-> Boolean.TRUE.equals(t.getBid()))
    				 .map((Task t )-> {
    	     				return (TaskDTO)transformers.convertEntityToDto(t, TaskDTO.class);
    	     			});
    			 })
     			.collect(Collectors.toList());
    			 
    	   	
    	return listDto;
    }
}
