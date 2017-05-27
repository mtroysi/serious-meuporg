package com.example.model;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

import javax.persistence.*;

@Entity(name = "link")
@Table(name = "link")
public class Link extends CommonEntity {

    /**
     * URL
     */
    @Column(name = "link")
    private String link;

    /**
     * Titre du lien
     */
    @Column(name = "title")
    private String title;

    /**
     * Tâche
     */
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
