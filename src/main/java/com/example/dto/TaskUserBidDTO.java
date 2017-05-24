package com.example.dto;

public class TaskUserBidDTO {

	private TaskDTO task;
	private UserDTO user;
	private Double duration;

	public TaskUserBidDTO() {
	}

	public TaskUserBidDTO(TaskDTO task, UserDTO user, Double duration) {
		this.task = task;
		this.user = user;
		this.duration = duration;
	}

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

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

}
