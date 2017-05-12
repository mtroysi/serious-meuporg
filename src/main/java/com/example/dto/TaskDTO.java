package com.example.dto;

import com.example.enumeration.PriorityEnum;
import com.example.model.Task;

import java.util.Date;


/**
 * Created by Florentin NOËL on 11/05/17.
 */
public class TaskDTO {
    private Long id;
    private String title;
    private String content;
    private PriorityEnum priority;
    private Date dateCreation;
    private Double duration;
    private Boolean isBid;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.content = task.getContent();
        this.priority = task.getPriority();
        this.dateCreation = task.getDateCreation();
        this.duration = task.getDuration();
        this.isBid = task.getBid();
    }

    public Task toModel() {
        Task task = new Task();
        task.setId(this.id);
        task.setTitle(this.title);
        task.setContent(this.content);
        task.setPriority(this.priority);
        task.setDateCreation(this.dateCreation);
        task.setDuration(this.duration);
        task.setBid(this.isBid);
        return task;
    }

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

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Boolean getBid() {
        return isBid;
    }

    public void setBid(Boolean bid) {
        isBid = bid;
    }
}
