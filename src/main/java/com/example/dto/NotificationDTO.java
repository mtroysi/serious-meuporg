package com.example.dto;

import java.util.Date;

import com.example.enumeration.TypeNotifEnum;

public class NotificationDTO {
	
    private Long id;
    private String title;
    private String content;
    private Date dateCreation;
    private Boolean isRead;
    private TypeNotifEnum type;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	public TypeNotifEnum getType() {
		return type;
	}
	public void setType(TypeNotifEnum type) {
		this.type = type;
	}
	
}
