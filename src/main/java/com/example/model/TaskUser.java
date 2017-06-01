package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.example.enumeration.StatusEnum;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "task_user")
@Table(name = "task_user")
public class TaskUser extends CommonEntity {

    /**
     * Utilisateur
     */
    @ManyToMany
    @JoinColumn(name = "user_id")
    private List<User> user;

    /**
     * Tâche
     */
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    /**
     * Statut
     */
    @Column(name = "status")
    private StatusEnum status;

    /**
     * Date de début
     */
    @Column(name = "date_begin")
    private Date dateBegin;

    /**
     * Durée réelle de réalisation
     */
    @Column(name = "duration_reel")
    private Double durationReel;

    /**
     * Colonne Kanban
     */
    @ManyToOne
    @JoinColumn(name = "colonne_kanban_id", nullable= true)
    private ColonneKanban colonneKanban;
    
    /**
     * Enchèrissements des utilisateurs
     */
    @OneToMany(mappedBy = "taskUser", cascade = CascadeType.ALL)
    private List<TaskUserBid> taskUserBids = new ArrayList<>();
    
    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
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

	public List<TaskUserBid> getTaskUserBids() {
		return taskUserBids;
	}

	public void setTaskUserBids(List<TaskUserBid> taskUserBids) {
		this.taskUserBids = taskUserBids;
	}
}
