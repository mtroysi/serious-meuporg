package com.example.model;

import javax.persistence.*;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "task_user_bid")
@Table(name = "task_user_bid")
public class TaskUserBid extends CommonEntity {

    /**
     * Utilisateur
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Tâche
     */
    @ManyToOne
    @JoinColumn(name = "task_user_id")
    private TaskUser taskUser;

    /**
     * Durée (enchère)
     */
    @Column(name = "duration")
    private Double duration;
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskUser getTaskUser() {
		return taskUser;
	}

	public void setTaskUser(TaskUser taskUser) {
		this.taskUser = taskUser;
	}

	public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }
}
