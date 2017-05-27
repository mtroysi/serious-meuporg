package com.example.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Florentin NOËL on 17/05/17.
 */
@Entity(name = "comment")
@Table(name = "comment")
public class Comment extends CommonEntity {

    /**
     * Auteur du commentaire
     */
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    /**
     * Tâche
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private Task task;

    /**
     * Contenu
     */
    @Column(name = "content")
    private String content;

    /**
     * Date de création
     */
    @Column(name = "date_creation")
    private Date dateCreation;

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
