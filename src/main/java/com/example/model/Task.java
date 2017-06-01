package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.enumeration.PriorityEnum;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "task")
@Table(name = "task")
public class Task extends CommonEntity {

    /**
     * Titre
     */
    @Column(name = "title")
    private String title;

    /**
     * Contenu
     */
    @Column(name = "content")
    private String content;

    /**
     * Priorité
     */
    @Column(name = "priority")
    private PriorityEnum priority;

    /**
     * Date de création
     */
    @Column(name = "date_creation")
    private Date dateCreation;

    /**
     * Echéance
     */
    @Column(name = "date_end")
    private Date dateEnd;

    /**
     * Durée
     */
    @Column(name = "duration")
    private Double duration;

    /**
     * Est aux enchères
     */
    @Column(name = "is_bid")
    private Boolean isBid;

    /**
     * Date fin enchère
     */
    @Column(name = "date_end_bid")
    private Date dateEndBid;

    /**
     * Argent rapporté par la tâche
     */
    @Column(name = "money")
    private Long money;

    /**
     * Expérience rapportée par la tâche
     */
    @Column(name = "xp")
    private Long experience;

    /**
     * Fréquence
     */
    @OneToOne
    @JoinColumn(name = "periodicity_id", nullable = true)
    private Periodicity periodicity;

    /**
     * Créateur
     */
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    /**
     * Tableau
     */
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    /**
     * Tags
     */
    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

    /**
     * Liens
     */
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Link> links = new ArrayList<>();

    /**
     * Checklists
     */
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Checklist> checklists = new ArrayList<>();

    /**
     * Destinataires de la tâches
     */
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskUser> taskUsers = new ArrayList<>();

    /**
     * Tâches parentes
     */
    @ManyToMany
    private List<Task> taskParents = new ArrayList<>();

    /**
     * Commentaires
     */
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comment> taskComments = new ArrayList<>();

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

    public boolean addTaskUsers(TaskUser e) {
		return taskUsers.add(e);
	}

    public Date getDateEndBid() {
        return dateEndBid;
    }

    public void setDateEndBid(Date dateEndBid) {
        this.dateEndBid = dateEndBid;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Task> getTaskParents() {
        return taskParents;
    }

    public void setTaskParents(List<Task> taskParents) {
        this.taskParents = taskParents;
    }

    public Boolean getIsBid() {
        return isBid;
    }

    public void setIsBid(Boolean isBid) {
        this.isBid = isBid;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public List<Comment> getTaskComments() {
        return taskComments;
    }

    public void setTaskComments(List<Comment> taskComments) {
        this.taskComments = taskComments;
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

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
}
