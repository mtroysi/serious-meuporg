package com.example.dto;

import java.util.List;

public class BidDTO {

	private Long idTask;
	private Double duration;
	private List<Long> listUserId;
	
	
	public Long getIdTask() {
		return idTask;
	}
	public void setIdTask(Long idTask) {
		this.idTask = idTask;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public List<Long> getListUserId() {
		return listUserId;
	}
	public void setListUserId(List<Long> listUserId) {
		this.listUserId = listUserId;
	}	
	
	
}
