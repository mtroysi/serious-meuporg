package com.example.model;

import com.example.enumeration.PriorityEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "task")
@Table(name = "task")
public class Task extends CommonEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "priority")
    private PriorityEnum priority;

    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "is_bid")
    private Boolean isBid;

    @Column(name = "creator")
    private User creator;

    @ManyToMany
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "task")
    private List<Link> links;

    @OneToMany(mappedBy = "task")
    private List<Checklist> checklists;

    @OneToMany(mappedBy = "task")
    private List<TaskUser> taskUsers;

    @OneToMany(mappedBy = "user")
    List<TaskUserBid> taskUserBids;

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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Checklist> getChecklists() {
        return checklists;
    }

    public void setChecklists(List<Checklist> checklists) {
        this.checklists = checklists;
    }

    public List<TaskUser> getTaskUsers() {
        return taskUsers;
    }

    public void setTaskUsers(List<TaskUser> taskUsers) {
        this.taskUsers = taskUsers;
    }

    public List<TaskUserBid> getTaskUserBids() {
        return taskUserBids;
    }

    public void setTaskUserBids(List<TaskUserBid> taskUserBids) {
        this.taskUserBids = taskUserBids;
    }
}
