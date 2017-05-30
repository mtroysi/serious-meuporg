package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * Created by Florentin NOËL on 11/05/17.
 */
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class TaskUserDTO {

	private Long id;
	private TaskWithPeriodDTO task;
	private UserDTO user;
	
	public TaskWithPeriodDTO getTask() {
		return task;
	}
	public void setTask(TaskWithPeriodDTO task) {
		this.task = task;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
