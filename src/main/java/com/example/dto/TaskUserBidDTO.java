package com.example.dto;

public class TaskUserBidDTO {

	private TaskUserDTO taskUser;
	private UserDTO user;
	private Double duration;

	public TaskUserBidDTO() {
	}

	public TaskUserBidDTO(TaskUserDTO taskUser, UserDTO user, Double duration) {
		this.taskUser = taskUser;
		this.user = user;
		this.duration = duration;
	}

	public TaskUserDTO getTaskUser() {
		return taskUser;
	}

	public void setTaskUser(TaskUserDTO task) {
		this.taskUser = task;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

}
