package com.example.dto;

import java.util.List;

public class BidDTO {

	private Long idTaskUser;
	private Double duration;
	private List<Long> listUserId;
	
	
	public Long getIdTaskUser() {
		return idTaskUser;
	}
	public void setIdTaskUser(Long idTaskUser) {
		this.idTaskUser = idTaskUser;
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
