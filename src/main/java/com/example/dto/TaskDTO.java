package com.example.dto;

import com.example.enumeration.PriorityEnum;
import com.example.model.Task;

import java.util.Date;


/**
 * Created by Florentin NOËL on 11/05/17.
 */
public class TaskDTO {
    private String title;
    private String content;
    private PriorityEnum priority;
    private Date dateCreation;
    private Double duration;
    private Boolean isBid;

    public TaskDTO(Task task) {
        this.title = task.getTitle();
        this.content = task.getContent();
        this.priority = task.getPriority();
        this.dateCreation = task.getDateCreation();
        this.duration = task.getDuration();
        this.isBid = task.getBid();
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
