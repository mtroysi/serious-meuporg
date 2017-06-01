package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    @JsonIgnore
    private String password;
    private String avatar;
    private Date dateCreation;
    private Long experience;
    private Long level;
    private Long money;
    private Boolean isSuperAdmin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatar(){
    	return avatar;
    }
    
    public void setAvatar(String avatar){
    	this.avatar=avatar;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

	public Boolean getIsSuperAdmin() {
		return isSuperAdmin;
	}

	public void setIsSuperAdmin(Boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}
}
