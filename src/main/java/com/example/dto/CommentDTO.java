package com.example.dto;

import com.example.model.User;

import java.util.Date;

/**
 * Created by Florentin NOËL on 17/05/17.
 */
public class CommentDTO {
    private Long id;
    private User creator;
    private String content;
    private Date dateCreation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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
