package com.example.dto;

public class TaskUserBidDTO {

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
