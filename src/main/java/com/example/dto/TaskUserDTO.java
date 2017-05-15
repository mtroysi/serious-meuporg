package com.example.dto;

import java.util.Date;

import com.example.enumeration.PriorityEnum;
import com.example.model.Task;


/**
 * Created by Florentin NOËL on 11/05/17.
 */
public class TaskUserDTO {

	private TaskDTO task;
	private UserDTO user;
	
	public TaskDTO getTask() {
		return task;
	}
	public void setTask(TaskDTO task) {
		this.task = task;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	
}
