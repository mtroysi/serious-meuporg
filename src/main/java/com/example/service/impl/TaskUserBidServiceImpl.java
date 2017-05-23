package com.example.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ConstanteGameMaster;
import com.example.dto.TaskDTO;
import com.example.dto.TaskUserBidDTO;
import com.example.dto.UserDTO;
import com.example.exception.GameMasterException;
import com.example.model.Task;
import com.example.model.TaskUserBid;
import com.example.model.User;
import com.example.repository.TaskRepository;
import com.example.repository.TaskUserBidRepository;
import com.example.repository.UserRepository;
import com.example.service.TaskUserBidService;
import com.example.service.UserService;
import com.example.transformers.Transformers;


@Service
public class TaskUserBidServiceImpl implements TaskUserBidService {
    
    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private TaskUserBidRepository taskUserBidRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private Transformers transformers;

	@Override
	public List<TaskUserBidDTO> getTaskUserBidByBoardAndUser(Long idBoard, Long idUser) {
		
		List<Task> listTask = taskRepo.findTaskBidByBoardIdAndDate(idBoard, new Date());
		User user = userRepo.findOne(idUser);
		if(user != null){
			UserDTO userDto = (UserDTO) transformers.convertEntityToDto(user, UserDTO.class);
			
			return listTask.stream().map((Task task) ->{
				TaskUserBid taskUserBid = task.getTaskUserBids().stream().filter((TaskUserBid tub) ->  tub.getUser().getId().equals(user.getId())).findFirst().orElse(null);
				if( taskUserBid == null ){
					return new TaskUserBidDTO((TaskDTO)transformers.convertEntityToDto(task, TaskDTO.class), null, null);
				}else{
					return new TaskUserBidDTO((TaskDTO)transformers.convertEntityToDto(task, TaskDTO.class), userDto, taskUserBid.getDuration() );
				}
			})	
			.filter(Objects::nonNull)
			.collect(Collectors.toList());
		}
		else{
			return null;	
		}
	}

	@Override
	public TaskUserBidDTO addOrUpdateTaskUserBid(Long idTask, Double duration) {
        User user = userService.getCurrentUser();
        
        TaskUserBid tub = taskUserBidRepo.findByTaskIdAndUserId(idTask, user.getId());
        
        if(tub != null){
        	tub.setDuration(duration);
        	tub = taskUserBidRepo.save(tub);
        }else{
        	Task task = taskRepo.findOne(idTask);
        	if( task != null){
        		TaskUserBid tub_new = new TaskUserBid();
	        	tub_new.setDuration(duration);
	        	tub_new.setTask(task);
	        	tub_new.setUser(user);
	        	tub = taskUserBidRepo.save(tub);
        	}else{
            	throw new GameMasterException(ConstanteGameMaster.TASK_NOT_FOUND_ERROR);
        	}
        }
        
		return (TaskUserBidDTO) transformers.convertEntityToDto(tub, TaskUserBidDTO.class);
	}

  
}
