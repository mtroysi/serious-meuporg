package com.example.model;

import javax.persistence.*;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "task_user_bid")
@Table(name = "task_user_bid")
public class TaskUserBid extends CommonEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "duration")
    private Double duration;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }
}
