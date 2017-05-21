package com.example.dto;

import com.example.enumeration.PriorityEnum;
import com.example.model.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    private Date dateEndBid;
    private Periodicity periodicity;
    private UserDTO creator;
    private BoardDTO board;

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
        this.title = title;    }

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

    public Date getDateEndBid() {
        return dateEndBid;
    }

    public void setDateEndBid(Date dateEndBid) {
        this.dateEndBid = dateEndBid;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public UserDTO getCreator() {
        return creator;
    }

    public void setCreator(UserDTO creator) {
        this.creator = creator;
    }

	public Boolean getIsBid() {
		return isBid;
	}

	public void setIsBid(Boolean isBid) {
		this.isBid = isBid;
	}

	public BoardDTO getBoard() {
		return board;
	}

	public void setBoard(BoardDTO board) {
		this.board = board;
	}
    
}
