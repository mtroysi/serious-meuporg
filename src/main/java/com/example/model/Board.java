package com.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "board")
@Table(name = "board")
public class Board extends CommonEntity {

    @Column(name = "name")
    private String name;
    
    @Column(name = "color")
    private String color;

    @Column(name = "date_creation")
    private Date dateCreation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="creator_id", nullable=false)
    private User creator;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardUser> boardUsers = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<BoardUser> getBoardUsers() {
        return boardUsers;
    }

    public void setBoardUsers(List<BoardUser> boardUsers) {
        this.boardUsers = boardUsers;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
