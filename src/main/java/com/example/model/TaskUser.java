package com.example.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "task_user")
@Table(name = "task_user")
public class TaskUser extends CommonEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "statut")
    private String statut;

    @Column(name = "date_begin")
    private Date dateBegin;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "duration_reel")
    private Double durationReel;

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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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

    public Double getDurationReel() {
        return durationReel;
    }

    public void setDurationReel(Double durationReel) {
        this.durationReel = durationReel;
    }
}
