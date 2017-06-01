package com.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Morgane TROYSI on 27/04/2017.
 */

@Entity(name = "user")
@Table(name = "user")
public class User extends CommonEntity {

    /**
     * Prénom
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Nom
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Adresse e-mail
     */
    @Column(name = "email", unique = true)
    private String email;

    /**
     * Mot de passe
     */
    @Column(name = "password")
    private String password;

    /**
     * Date d'inscription
     */
    @Column(name = "date_creation")
    private Date dateCreation;

    /**
     * Avatar
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * Argent
     */
    @Column(name = "money")
    private Long money = 0L;

    /**
     * Niveau
     */
    @Column(name = "level")
    private Long level = 1L;

    /**
     * Expérience
     */
    @Column(name = "xp")
    private Long experience = 0L;

    /**
    *
    */
    @Column(name = "super_admin")
    private Boolean isSuperAdmin = false;

    /**
     * Tableaux
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BoardUser> boardUsers = new ArrayList<>();

    /**
     * Tâches
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TaskUser> taskUsers = new ArrayList<>();

    /**
     * Tâches en enchères
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TaskUserBid> taskUserBids = new ArrayList<>();

    /**
     * Notifications
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();
    
    /**
     * Item User
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemUser> itemUser = new ArrayList<>();


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<BoardUser> getBoardUsers() {
        return boardUsers;
    }

    public void setBoardUsers(List<BoardUser> boardUsers) {
        this.boardUsers = boardUsers;
    }

    public List<TaskUser> getTaskUsers() {
        return taskUsers;
    }

    public void setTaskUsers(List<TaskUser> taskUsers) {
        this.taskUsers = taskUsers;
    }

    public List<TaskUserBid> getTaskUserBids() {
        return taskUserBids;
    }

    public void setTaskUserBids(List<TaskUserBid> taskUserBids) {
        this.taskUserBids = taskUserBids;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public boolean addNotification(Notification arg0) {
		return notifications.add(arg0);
	}
	
	public boolean addItemUser(ItemUser arg0) {
		return itemUser.add(arg0);
	}

	public List<ItemUser> getItemUser() {
		return itemUser;
	}

	public void setItemUser(List<ItemUser> itemUser) {
		this.itemUser = itemUser;
	}

	public Boolean getIsSuperAdmin() {
		return isSuperAdmin;
	}

	public void setIsSuperAdmin(Boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}
    
}
