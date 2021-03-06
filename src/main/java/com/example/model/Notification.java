package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.enumeration.TypeNotifEnum;

/**
 * Created by Adrien CASELLES on 11/05/17.
 */
@Entity(name = "notification")
@Table(name = "notification")
public class Notification extends CommonEntity {

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
     * Destinataire de la notification
     */
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    /**
     * Date de création
     */
    @Column(name = "date_creation")
    private Date dateCreation;

    /**
     * A été lue ou non par l'utilisateur
     */
    @Column(name = "is_read")
    private Boolean isRead;

    /**
     * Type de la notification
     */
    @Column(name = "type")
    private TypeNotifEnum type;
    
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public TypeNotifEnum getType() {
		return type;
	}

	public void setType(TypeNotifEnum type) {
		this.type = type;
	}
	
}
