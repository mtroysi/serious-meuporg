package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "board")
@Table(name = "board")
public class Board extends CommonEntity {

    /**
     * Nom du tableau.
     */
    @Column(name = "name")
    private String name;

    /**
     * Couleur du tableau.
     */
    @Column(name = "color")
    private String color;

    /**
     * Date de création du tableau.
     */
    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "money_win_bid")
    private Integer moneyWinBid;

    @Column(name = "exp_win_bid")
    private Integer expWinBid;

    @Column(name = "money_done_task")
    private Integer moneyDoneTask;

    @Column(name = "exp_done_task")
    private Integer expDoneTask;

    /**
     * Créateur du tableau
     */
    @ManyToOne
    @JoinColumn(name="creator", nullable=false)
    private User creator;

    /**
     * Tâches du tableau
     */
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    /**
     * Utilisateurs du tableau
     */
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardUser> boardUsers = new ArrayList<>();

    /**
     * Colonnes du tableau en vue Kanban
     */
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<ColonneKanban> colonneKanbans = new ArrayList<>();

    /** Getters & setters */

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<ColonneKanban> getColonneKanbans() {
		return colonneKanbans;
	}

	public void setColonneKanbans(List<ColonneKanban> colonneKanbans) {
		this.colonneKanbans = colonneKanbans;
	}

	public Integer getMoneyWinBid() {
		return moneyWinBid;
	}

	public void setMoneyWinBid(Integer moneyWinBid) {
		this.moneyWinBid = moneyWinBid;
	}

	public Integer getExpWinBid() {
		return expWinBid;
	}

	public void setExpWinBid(Integer expWinBid) {
		this.expWinBid = expWinBid;
	}

	public Integer getMoneyDoneTask() {
		return moneyDoneTask;
	}

	public void setMoneyDoneTask(Integer moneyDoneTask) {
		this.moneyDoneTask = moneyDoneTask;
	}

	public Integer getExpDoneTask() {
		return expDoneTask;
	}

	public void setExpDoneTask(Integer expDoneTask) {
		this.expDoneTask = expDoneTask;
	}
}
