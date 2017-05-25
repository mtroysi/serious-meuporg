package com.example.model;

import com.example.enumeration.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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

    @Column(name = "status")
    private StatusEnum status;

    @Column(name = "date_begin")
    private Date dateBegin;

    @Column(name = "duration_reel")
    private Double durationReel;
    
    @ManyToOne
    @JoinColumn(name = "colonne_kanban_id", nullable= true)
    private ColonneKanban colonneKanban;

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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Double getDurationReel() {
        return durationReel;
    }

    public void setDurationReel(Double durationReel) {
        this.durationReel = durationReel;
    }

	public ColonneKanban getColonneKanban() {
		return colonneKanban;
	}

	public void setColonneKanban(ColonneKanban colonneKanban) {
		this.colonneKanban = colonneKanban;
	}
}
