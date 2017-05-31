package com.example.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.enumeration.PriorityEnum;
import com.example.enumeration.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;


public class TaskWithPeriodDTO {

    private Long id;
    private Long boardId;
    private String title;
    private String content;
    private PriorityEnum priority;
    private Date dateCreation;
    private Double duration;
    private Boolean isBid;
    private Date dateEndBid;
    private StatusEnum status;
    private Double durationReel;
    private Long creator;
    private Date dateBegin;
    private Date dateEnd;
    private Date dateBeginTask; /* Valeur reel de la tache (gestion de la périodicity) */
    private Date dateEndTask; /* Valeur reel de la tache (gestion de la périodicity) */
    private Boolean isPeriodicity = false;
    private PeriodicityDTO periodicity;
    private Long money;
    private Long experience;
    private ColonneKanbanDTO colonneKanban;
    private List<TagDTO> tags = new ArrayList<>();
    private List<CommentDTO> taskComments = new ArrayList<>();

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Double getDurationReel() {
        return durationReel;
    }

    public void setDurationReel(Double durationReel) {
        this.durationReel = durationReel;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateBeginTask() {
        return dateBeginTask;
    }

    public void setDateBeginTask(Date dateBeginTask) {
        this.dateBeginTask = dateBeginTask;
    }

    public Date getDateEndTask() {
        return dateEndTask;
    }

    public void setDateEndTask(Date dateEndTask) {
        this.dateEndTask = dateEndTask;
    }

    public Boolean getIsPeriodicity() {
        return periodicity != null;
    }

    public void setIsPeriodicity(Boolean isPeriodicity) {
        this.isPeriodicity = isPeriodicity;
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

    public Boolean getIsBid() {
        return isBid;
    }

    public void setIsBid(Boolean isBid) {
        this.isBid = isBid;
    }

    public Date getDateEndBid() {
        return dateEndBid;
    }

    public void setDateEndBid(Date dateEndBid) {
        this.dateEndBid = dateEndBid;
    }

    public PeriodicityDTO getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(PeriodicityDTO periodicity) {
        this.periodicity = periodicity;
    }

    public ColonneKanbanDTO getColonneKanban() {
        return colonneKanban;
    }

    public void setColonneKanban(ColonneKanbanDTO colonneKanban) {
        this.colonneKanban = colonneKanban;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public List<CommentDTO> getTaskComments() {
        return taskComments;
    }

    public void setTaskComments(List<CommentDTO> taskComments) {
        this.taskComments = taskComments;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(UserDTO creator) {
        this.creator = creator.getId();
    }
}
